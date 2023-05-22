# Convention Rule

1. xml 형태로 버전 관리가 어렵다 보니 업데이트를 할 때마다 날짜 폴더(e.g. 0401)를 만들어서 업데이트를 합니다.
2. cli 사용 편의성을 위해 파일 명은 영어로 합니다.
3. export 할 때 특정 시스템 다이어그램과 다이어그램에 포함하는 요소(액터, 유스케이스 ..etc)들을 하나의 패키지(패키지 명은 마케팅/영업시스템와 같은 명칭을 사용합니다.)에 넣어서 export를 합니다.

# How can i use database bootstrap script?

1. .env 파일을 생성한다.
2. .env 파일을 아래 양식에 맞춰 작성한다.
```
DB_ID=아이디를_입력해주세요
DB_PASSWORD=비밀번호를_입력해주세요
```
3. terminal 환경에서 ./bootstrap-database.sh를 실행한다. (만약 실행 할 수 없는 경우 `chmod +x ./bootstrap-database.sh`를 입력해준다.)
   * docker, local 환경을 지원한다.
   * ./sql/initialization.sql을 기반으로 database를 만드니 수정할 부분이 있다면 해당 sql을 수정해주면 된다.
   * database 이름은 기존 DB 충돌을 방지하기 위해 nemne_insurance라는 db를 사용한다. 
