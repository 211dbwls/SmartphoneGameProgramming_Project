# SmartphoneGameProgramming
2022 1학기 스마트폰게임프로그래밍

# Project - JELLYKING
## 1차 발표 : 5주차 수요일 (3/30)
* 게임 컨셉
  * 공 튀기기 게임
  * 장애물을 피해 이동하면서 열쇠를 모아 스테이지를 클리어하는 게임
  ![jellyking1](https://user-images.githubusercontent.com/65964035/160432688-88a7f5e1-42a1-46b0-989c-f57195af37e4.png)
* 개발 범위
  * 플레이어 구현
    * 위아래로 계속 움직임
    * 좌우 터치로 이동 가능
    * 터치하는 길이에 따라 길게 이동 가능
  * 스테이지 5개 구현
  * 블록 6종류 구현
    | 종류 | 내용 |
    |----|-------------------|
    | 일반 블록 | 밟을 수 있는 기본 블록 | 
    | 부서지는 블록 | 밟으면 없어지는 블록 |
    | 점프 블록 | 밟으면 더 높이 점프할 수 있는 블록 | 
    | 직진 블록 | 밟으면 앞으로 이동하는 블록 | 
    | 이동 블록 | 좌우로 움직이는 블록 | 
    | 전기 블록 | 닿으면 죽는 블록 | 
  * 아이템 2종류 구현
    | 종류 | 내용 | 
    |----|-------------------|
    | 점프1 | 2단 점프 가능(1회만) |  
    | 점프2 | 2단 점프 가능(한 스테이지 내에서 무제한 사용) | 
  * 적 3종류 구현
    | 종류 | 내용 |
    |----|-------------------|
    | 고정 | 움직이지 않으며 충돌시 죽음 | 
    | 상하이동 | 일정 시간마다 위에서 아래로 떨어지며 충돌시 죽음 |
    | 좌우이동 | 좌우로 이동하며 충돌시 죽음 | 
 * 예상 게임 실행 흐름
   * 장애물과 적을 피해 코인 획득
   ![jellyking2](https://user-images.githubusercontent.com/65964035/160439579-2115c4b9-f290-409b-9e24-869f94adfe44.jpg)
   * 열쇠 획득
   ![jellyking3](https://user-images.githubusercontent.com/65964035/160441617-9e6c7bc4-1db6-4fa1-8fce-aede3d32412e.jpg)
   * 클리어 후 다음 스테이지로 이동
   ![jellyking4](https://user-images.githubusercontent.com/65964035/160441309-764e5068-50ee-428d-929b-3d935cf4f03b.jpg)
 * 개발 일정
   | 주차 | 개발 내용 |
   |-------------------|-------------------|
   | 1주차(4/04 - 4/10) | 리소스 수집 | 
   | 2주차(4/11 - 4/17) | 스테이지 기본 틀 구현 |
   | 3주차(4/18 - 4/24) | 플레이어 캐릭터 이동 구현 | 
   | 4주차(4/25 - 5/01) | 블록 구현(1) | 
   | 5주차(5/02 - 5/08) | 블록 구현(2) 및 적 구현(1) | 
   | 6주차(5/09 - 5/15) | 적 구현(2) 및 아이템 구현(1) |  
   | 7주차(5/16 - 5/22) | 코인 및 열쇠 구현 |
   | 8주차(5/23 - 5/29) | 게임 시작 및 종료 처리 |  
   | 9주차(5/30 - 6/05) | 최종 점검 | 
 * 발표 동영상
   * [발표_동영상_링크] (https://youtu.be/tIb4yV2oLnU)
