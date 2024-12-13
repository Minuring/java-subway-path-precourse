## 구현할 기능 목록

### 초기 설정
- [ ] 프로그램 시작 시 역, 노선, 구간 정보를 초기 설정한다.
  - 거리와 소요 시간은 양의 정수이며 단위는 km와 분을 의미한다.
    ```
     1. 지하철역으로 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역이 등록되어 있다.
     2. 지하철 노선으로 2호선, 3호선, 신분당선이 등록되어 있다.
     3. 노선에 역이 아래와 같이 등록되어 있다.(왼쪽 끝이 상행 종점)
       - 2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역
       - 3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역
       - 신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역
     ```

### 경로 조회 기능
- [ ] 출발역과 도착역을 입력받아 경로를 조회한다.
- [ ] 경로 조회 시 총 거리, 총 소요 시간도 함께 출력한다.
    - [x] `최단 거리` 기준 경로 조회 
    - [x] `최소 시간` 기준 조회

### 예외 처리
- [ ] 경로 조회 시 출발역과 도착역이 같으면 에러를 출력한다.
- [ ] 경로 조회 시 출발역과 도착역이 연결되어 있지 않으면 에러를 출력한다.
- [ ] 그 외 정상적으로 프로그램이 수행되지 않은 경우 에러를 출력한다.

<br>

## ✍🏻 입출력 요구사항
- [ ] 기대하는 출력 결과는 `[INFO]`를 붙여서 출력한다. 출력값의 형식은 예시와 동일하게 한다.
- [ ] 에러 발생 시 `[ERROR]`를 붙여서 자유롭게 출력한다.

### 💻 프로그래밍 실행 결과 예시
#### 경로 조회
```
## 메인 화면
1. 경로 조회
Q. 종료

## 원하는 기능을 선택하세요.
1

## 경로 기준
1. 최단 거리
2. 최소 시간
B. 돌아가기

## 원하는 기능을 선택하세요.
1

## 출발역을 입력하세요.
교대역

## 도착역을 입력하세요.
양재역

## 조회 결과
[INFO] ---
[INFO] 총 거리: 4km
[INFO] 총 소요 시간: 11분
[INFO] ---
[INFO] 교대역
[INFO] 강남역
[INFO] 양재역

## 메인 화면
1. 경로 조회
Q. 종료

...
```

#### 에러 출력 예시

```
## 메인 화면
1. 경로 조회
Q. 종료

## 원하는 기능을 선택하세요.
1

## 경로 기준
1. 최단 거리
2. 최소 시간 
B. 돌아가기

## 원하는 기능을 선택하세요.
1

## 출발역을 입력하세요.
강남역

## 도착역을 입력하세요.
강남역

[ERROR] 출발역과 도착역이 동일합니다.

## 경로 기준
1. 최단 거리
2. 최소 시간 
B. 돌아가기

## 원하는 기능을 선택하세요.

...

```

### 프로그래밍 요구사항 - Application
- [ ] Check
- Application 클래스를 활용해 구현해야 한다.
- Application의 패키지 구조는 변경하지 않는다.
- Application 클래스에 있는 Scanner를 사용하고 별도의 Scanner 객체를 만들지 않는다.

### 프로그래밍 요구사항 - Station, Line
- [ ] Check
- Station, Line 클래스를 활용하여 지하철역과 노선을 구현해야 한다.
- 제공하는 각 클래스의 기본 생성자를 추가할 수 없다.
- 필드(인스턴스 변수)인 name의 접근 제어자 private을 변경할 수 없다.
- 가능하면 setter 메소드(ex. setXXX)를 추가하지 않고 구현한다.

```java
public class Station {
    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}

```

### 프로그래밍 요구사항 - StationRepository, LineRepository
- [ ] Check
- Station과 Line의 상태를 저장할 수 있는 StationRepository, LineRepository를 제공한다.
- 필요 시 StationRepository, LineRepository 이 외 추가로 Repository를 만들 수 있다.
- 추가로 생성되는 객체에 대해서 XXXRepository 네이밍으로 저장 클래스를 추가한다.
- 객체들의 상태를 관리하기 위해서 XXXRepository 클래스를 활용해 저장 로직을 구현해야 한다.
- 작성된 메서드는 수정할 수 없고, 필요에 따라 메서드를 자유롭게 추가할 수 있다.

```java
public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }
}
```