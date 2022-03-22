# grpc-http-compare

### 대략적인 비교

ref. _https://docs.microsoft.com/ko-kr/aspnet/core/grpc/comparison?view=aspnetcore-5.0_
<table>
<thead>
    <tr>
        <th>기능</th>
        <th>gRPC</th>
        <th>JSON을 사용하는 HTTP API</th>
    </tr>
</thead>
<tbody>
    <tr>
        <td>계약</td>
        <td>필수(.proto)</td>
        <td>선택 사항(OpenAPI)</td>
    </tr>
    <tr>
        <td>프로토콜</td>
        <td>HTTP/2</td>
        <td>HTTP</td>
    </tr>
    <tr>
        <td>Payload</td>
        <td>Protobuf(소형,이진)</td>
        <td>JSON(대형, 사람이 읽을 수 있음</td>
    </tr>
    <tr>
        <td>규범</td>
        <td>엄격한 사양</td>
        <td>느슨함. 모든 HTTP가 유효합니다.</td>
    </tr>
    <tr>
        <td>스트리밍</td>
        <td>클라이언트, 서버, 양방향</td>
        <td>클라이언트, 서버</td>
    </tr>
    <tr>
        <td>브라우저 지원</td>
        <td>아니요(gRPC-웹 필요)</td>
        <td>예</td>
    </tr>
    <tr>
        <td>보안</td>
        <td>전송(TLS)</td>
        <td>전송(TLS)</td>
    </tr>
    <tr>
        <td>클라이언트 코드 생성</td>
        <td>예</td>
        <td>OpenAPI + 타사도구</td>
    </tr>
</tbody>
</table>

[proto guide](https://developers.google.com/protocol-buffers/docs/proto3)  
[gRPC 사양](https://github.com/grpc/grpc/blob/master/doc/PROTOCOL-HTTP2.md)

### 모듈 구성

#### test-grpc-interface

> * gRPC 통신을 위한 interface library
>* server와 client 모듈에 의존성 주입되어야함
>* .proto file만 작성하고, build.gradle 설정으로 interface 생성됨
>* IntelliJ gradle pane에서 빌드후, localMaven repository에 publishing (Tasks -> publishing -> publishToMavenLocal 혹은 터미널에서 ]$ ./gradlew clean publishToMavenLocal)

#### test-api-server

> * test-grpc-interface를 통해서 빌드된 jar lib.를 dependency에 추가해야함.
>```groovy
>implementation 'com.ijys:test-grpc-interface:0.0.1-SNAPSHOT'
>````
>* maven local repository를 참조하기 위해서 build.gradle의 repositories에 mavenLocal() 추가해야함.
>* 아래의 몇가지 command로 서버 상태를 확인할 수 있다.
>```shell
>]$ grpcurl --plaintext localhost:9090 list
>com.ijys.internals.SimpleApi
>grpc.health.v1.Health
>grpc.reflection.v1alpha.ServerReflection
>
>]$ grpcurl --plaintext localhost:9090 list com.ijys.internals.SimpleApi
>com.ijys.internals.SimpleApi.GetSimpleData
>
>]$ grpcurl --plaintext localhost:9090 describe com.ijys.internals.SimpleApi.GetSimpleData
>com.ijys.internals.SimpleApi.GetSimpleData is a method:
>rpc GetSimpleData ( .com.ijys.internals.SimpleRequest ) returns ( .com.ijys.internals.SimpleReply );
>
>]$ grpcurl --plaintext -d '{"name": "jaye.jung"}' localhost:9090 com.ijys.internals.SimpleApi.GetSimpleData
>{
>"id": "1",
>"data": "My name is jaye.jung"
>}
 >```

#### test-api-client

> * test-grpc-interface를 통해서 빌드된 jar lib.를 dependency에 추가해야함.
>```groovy
>implementation 'com.ijys:test-grpc-interface:0.0.1-SNAPSHOT'
>````
>* maven local repository를 참조하기 위해서 build.gradle의 repositories에 mavenLocal() 추가해야함.
>* rest API와 gRPC API 클라이언트 구현

