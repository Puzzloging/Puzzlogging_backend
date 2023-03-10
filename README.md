# 🚶‍♂️ Puzzlogging
플로깅을 통해 모은 쓰레기 사진들로 원하는 사진을 모자이크 패턴으로 제작해주는 어플

## 1. 제작 기간 & 참여 인원

- 2022년 12월 ~ 2023년 1월 
- 팀 프로젝트

## 2. 사용 기술

- Java 17
- Spring Boot 3.0
- Gradle
- Spring Data JPA
- MySQL 8.0


- AWS EC2
- AWS RDS
- AWS S3


- Python
- opencv-python
- numpy
- Pillow

## 3. 핵심 기능

이 서비스의 핵심 기능은 모자이크 패턴 이미지 생성 기능입니다.

<details>
<summary>핵심 내용 펼처 보기</summary>

### 3.1 전체 흐름

![total_flow](https://user-images.githubusercontent.com/40589394/216044274-3af13f41-cb93-4f89-ac15-fdfc37f369fb.png)

### 3.2 Controller 📌[코드](https://github.com/Puzzloging/Puzzlogging_backend/blob/main/src/main/java/com/example/Puzzlogging/domain/photomosaic/controller/PhotoMosaicImageController.java)

![controller](https://user-images.githubusercontent.com/40589394/216045715-11ff897e-6c40-4205-bbcf-5b2d5a674d99.png)

- 요청 처리
  - 받은 요청을 Service 계층에 로직 처리를 위임합니다.
- 결과 응답
  - Service 계층에서 받은 로직 처리 결과를 JSON 형태로 응답합니다.

### 3.3 Service 📌[코드](https://github.com/Puzzloging/Puzzlogging_backend/blob/main/src/main/java/com/example/Puzzlogging/domain/photomosaic/service/PhotoMosaicImageServiceImpl.java)

![service1](https://user-images.githubusercontent.com/40589394/216048727-d391d5f1-6728-4e2c-a01e-1920aedcd09b.png)

- 작업 폴더 생성 및 락 확인
    - 모자이크 패턴 이미지 생성 작업을 수행할 폴더를 만들고 다른 요청에서 사용하고 있는지를 락을 통해 확인합니다.
    - 만약 락이 걸려 있다면 에러를 Throw하고 없다면 락을 겁니다.

![service2](https://user-images.githubusercontent.com/40589394/216050175-feed11d9-6289-49d4-a37e-ecb96eb01354.png)

- 메인 사진과 쓰레기 사진 불러오기
  - 모자이크 패턴으로 만들 메인 사진과 소비될 쓰레기 사진을 작업 폴더에 위치시킵니다.

![service3](https://user-images.githubusercontent.com/40589394/216050852-b252954d-76d7-4274-abb0-241d5df5a1b9.png)

- 모자이크 패턴 사진 생성 및 S3 업로드
  - 작업 폴더에서 모자이크 패턴 사진을 생성하고 S3에 업로드합니다.

![service4](https://user-images.githubusercontent.com/40589394/216051305-1591add8-ddf1-48cb-829f-27a4a3bf3a22.png)

- 삭제
  - 사용된 쓰레기 사진들을 삭제하고 작업했던 폴더도 삭제합니다.

### 3.4 Generator 📌[코드](https://github.com/Puzzloging/Puzzlogging_backend/blob/main/src/main/java/com/example/Puzzlogging/utils/photomosaic_generator/PhotoMosaicGenerator.java)

![generator](https://user-images.githubusercontent.com/40589394/216052501-9fe640fd-30a1-48d5-a8d1-ca30f3527a1a.png)

- 작업폴더에서 모자이크 패턴 사진 생성
  - 실행되고 있는 서버안의 작업 공간에서 Python 프로그램을 실행시킵니다.


- 작업 위치 📌[코드](https://github.com/Puzzloging/Puzzlogging_backend/tree/main/photomosaic_generator)

### 3.5 Repository 

![repository](https://user-images.githubusercontent.com/40589394/216053693-9d60308f-5d22-4bd4-b986-950b95c4b7cc.png)

- 업로드 주소 저장
  - 모자이크 패턴 사진을 S3업로드 후 업로드 주소를 DB에 저장합니다.

</details> 

## 4. 핵심 트러블 슈팅

### 문제
위의 핵심 기능을 처음 개발했을 때는 작업 공간을 멤버별로 나누지 않았습니다.

그 후 Postman을 통해 위 기능을 테스트해 보았을 때 하나의 요청에서는 문제없이 동작했습니다.

그러던 중 여러 요청이 한 번에 들어올 경우를 생각하지 않은 것이 생각났고 테스트 결과 역시나 문제가 발생했습니다.

### 원인
서로 다른 요청이 같은 작업 공간에서 자원을 소모하려해 데드락이 발생했던 것입니다.

### 해결책
우선 멤버별로 작업 공간을 만들어줘서 같은 자원을 사용하지 않도록 했습니다.

또한 멤버는 같지만 요청이 같을 경우 작업 공간에 lock 파일을 생성하여 락이 해제되어 있는 상태일 때만 작업을 할 수 있도록 동기화 처리를 했습니다.
