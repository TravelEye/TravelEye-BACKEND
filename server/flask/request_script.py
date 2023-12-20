import requests

# Flask 서버가 실행 중인 주소
url = 'http://127.0.0.1:5000/predict'

# POST 요청에 보낼 JSON 데이터
# 이 부분은 Flask 애플리케이션에서 요구하는 형식과 데이터에 맞게 조정해야 합니다.
data = {
    'GENDER': 1,  # 예: '남성'을 0, '여성'을 1로 인코딩
    'AGE_GRP': 20,  # 예: 나이 그룹
    'INCOME': 4,  # 예: 소득
    'TRAVEL_STYL_1': 3,  # 예: 여행 스타일에 대한 응답
    'TRAVEL_STYL_2': 2,
    'TRAVEL_STYL_3': 1,
    'TRAVEL_STYL_4': 4,
    'TRAVEL_STYL_5': 1,
    'TRAVEL_STYL_6': 1,
    'TRAVEL_STYL_7': 1,
    'TRAVEL_STYL_8': 1
}

# POST 요청 보내기
response = requests.post(url, json=data)

# 응답 출력
print(response.json())
