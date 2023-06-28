# 🎮 SmartphoneGameProgramming
2022 1학기 스마트폰게임프로그래밍

# 👻 Project - JELLYKING
## 👩‍🏫 1차 발표 : 5주차 수요일 (3/30)
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
  * 적 4종류 구현
    | 종류 | 내용 |
    |----|-------------------|
    | 고정 | 움직이지 않으며 충돌시 죽음 | 
    | ~~상하이동~~ | ~~일정 시간마다 위에서 아래로 떨어지며 충돌시 죽음~~ |
    | 좌우이동 | 좌우로 이동하며 충돌시 죽음. 적의 머리를 밟을 경우 적이 죽음 | 
    | 추락 | 일정 시간마다 위에서 아래로 떨어지며 충돌시 죽음 |
    | 상하이동 | 일정 시간마다 위아래로 움직이며 충돌시 죽음 |
 * 예상 게임 실행 흐름
   * ~~장애물과 적을 피해 코인 획득~~ -> 장애물과 적을 피해 별 획득
   ![jellyking2](https://user-images.githubusercontent.com/65964035/160439579-2115c4b9-f290-409b-9e24-869f94adfe44.jpg)
   * ~~열쇠 획득~~ -> 모든 별 획득하면 스테이지 클리어
   ![jellyking3](https://user-images.githubusercontent.com/65964035/160441617-9e6c7bc4-1db6-4fa1-8fce-aede3d32412e.jpg)
   * 클리어 후 다음 스테이지로 이동
   ![jellyking4](https://user-images.githubusercontent.com/65964035/160441309-764e5068-50ee-428d-929b-3d935cf4f03b.jpg)
 * 개발 일정
   | 주차 | 개발 내용 |
   |-------------------|-------------------|
   | 1주차(4/04 - 4/10) | 리소스 수집 | 
   | 2주차(4/11 - 4/17) | 스테이지 기본 틀 구현 |
   | 3주차(4/18 - 4/24) | 플레이어 캐릭터 이동 구현 | 
   | 4주차(4/25 - 5/01) | 블록 구현 | 
   | 5주차(5/02 - 5/08) | ~~블록 구현(2) 및 적 구현(1)~~ -> 적 구현 | 
   | 6주차(5/09 - 5/15) | ~~적 구현(2) 및 아이템 구현(1)~~ -> 아이템 및 별 구현 |  
   | 7주차(5/16 - 5/22) | ~~코인 및 열쇠 구현~~ -> 게임 시작 및 종료 구현 |
   | 8주차(5/23 - 5/29) | ~~게임 시작 및 종료 처리~~ -> 사운드 및 UI 구현 |  
   | 9주차(5/30 - 6/05) | 최종 점검 | 
 * 발표 동영상
   * [발표_동영상_링크] (https://youtu.be/tIb4yV2oLnU) 

## 👩‍🏫 2차 발표 : 10주차 수요일 (5/04)
* 게임 소개
  * 공 튀기기 게임
  * 장애물을 피해 이동하면서 별을 모아 스테이지를 클리어하는 게임
  ![jellyking1](https://user-images.githubusercontent.com/65964035/160432688-88a7f5e1-42a1-46b0-989c-f57195af37e4.png)
* 현재까지 진행 상황
   | 진행 상황 | 내용 | 진행 정도 |
   |-------------------|-------------------|-------------------|
   | 리소스 수집 | 필요한 리소스 모두 수집 완료 | 100% | 
   | 스테이지 기본 틀 구현 | 스테이지 5개 모두 구현 완료 | 100% |
   | 플레이어 캐릭터 이동 구현 | 점프 구현 및 이동 속도 수정 필요 | 80% | 
   | 블록 구현 | 충돌 처리 수정 필요 | 80% | 
   | 적 구현 | 일부 적 이동 구현 수정 필요 | 80% | 
   | 아이템 구현 | 충돌 체크 및 처리 구현 필요 | 20% |  
   | 별 구현 | | 0% | 
   | 게임 시작 및 종료 구현 | | 0% |
   | 사운드 및 UI 구현 | | 0% |  
 * git commit
   * 2주차(04/10 일 - 04/16 토)
   ![commit 0404-0410](https://user-images.githubusercontent.com/65964035/166151705-2c4d7461-5016-4a54-8fb0-fb58225b7ca6.PNG)
   * 3주차(04/17 일 - 04/23 토)
   ![commit 0411-0417](https://user-images.githubusercontent.com/65964035/166151724-38f0374d-a04b-4d64-a9f8-ed47664df600.PNG)
   * 4주차(04/24 일 - 04/30 토)
   ![commit 0418-0424](https://user-images.githubusercontent.com/65964035/166151739-d4553238-15d8-4a69-a04b-e161aeb45ac9.PNG)
   * 5주차(05/01 일 - 5/03 화)
   ![commit 0425-](https://user-images.githubusercontent.com/65964035/166416640-7e889ee3-adbd-4d3a-95bf-0d85c014f43e.PNG)
   * 주별 commit수
     | 주차 | commit수 |
     |-------------------|-------------------|
     | 1주차(4/04 - 4/10) | 0 | 
     | 2주차(4/11 - 4/17) | 12 |
     | 3주차(4/18 - 4/24) | 24 | 
     | 4주차(4/25 - 5/01) | 18 | 
     | 5주차(5/02 - 5/03) | 28 |   
 * 변경사항
   * 좌우이동 적 - 좌우로 이동하며 충돌시 플레이어 죽음. + 플레이어가 적의 머리를 밟을 경우 적이 죽음.
     * 이유 : 좌우이동 적을 넘고 지나가기에는 점프 높이와 거리에 한계가 있어, 밟을 경우 적이 죽는 설정을 추가함.
   * 상하이동 적을 위에서 아래로 떨어지기만 하는 적과 위아래로 움직이는 적 2종류로 나눔.
     * 이유 : 맵 구성에 맞는 적을 설정하기 위해 2종류로 나눔.
 * MainGame에 등장하는 game object
   * JellyKing
     * class 정보
        * 터치 위치에 따라 이동 방향 설정
        * 터치 길이에 따라 이동 거리 설정
        * 이동 및 점프
     * 게임 내에서 class가 책임지는 핵심 코드
       ```
       public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        dx = Metrics.size(R.dimen.jellyking_move_speed);
        dy = Metrics.size(R.dimen.jellyking_jump_speed);

        float dx = this.dx * frameTime;
        float dy = this.dy * frameTime;

        /* 터치 시간 구하기 */
        if(touch == true) {  // 터치 중인 경우
            touchTime += frameTime;
        }
        else {  // 터치가 끝났을 경우
            touchTime = 0;
        }

        /* 직진 블록과 충돌한 경우 */
        if(collisionStraightLeftBlock == true) {
            if(dx >= 0) {  // 왼쪽으로 이동
                dx = -dx;
            }
            dy = collisionStraightLeftBlockY - y;  // 충돌 위치로 y 고정

            if(touch == true) {  // 이동 중 터치했을 경우
                collisionStraightLeftBlock = false;
                dy = this.dy * frameTime;
            }
            else if(collisionBlock == true) {  // 벽돌과 충돌했을 경우
                collisionStraightLeftBlock = false;
            }
        }
        else if(collisionStraightRightBlock == true) {
            dy = collisionStraightRightBlockY - y;  // 충돌 위치로 y 고정

            if(touch == true) {  // 이동 중 터치했을 경우
                collisionStraightRightBlock = false;
                dy = this.dy * frameTime;
            }
            else if(collisionBlock == true) {  // 벽돌과 충돌했을 경우
                collisionStraightRightBlock = false;
            }
        }

        /* 점프 블록과 충돌한 경우 */
        if(collisionJumpBlock == true) {
            jumpHeightLimit = JUMP_HEIGHT_LIMIT_LONG;
            collisionJumpBlock = false;
        }
        else if(collisionJumpBlock == false && jumpUp == false){
            jumpHeightLimit = JUMP_HEIGHT_LIMIT_SHORT;
        }

        /* 점프 */
        if(jumpUp == true) {  // 위로 이동 중인 경우
            jumpHeight += dy;
            if (jumpHeight > jumpHeightLimit) {  // 위에 닿았을 경우
                jumpHeight = 0;
                jumpUp = false;  // 아래로 이동하도록
            }
            if(dy > 0) {
                dy = -dy;
            }
        }

        /* 터치 */
        if(touch == true)  {  // 터치한 경우
            /* 터치 시간에 따라 이동 거리 설정 */
            if(touchTime > 0.5f) {  // 터치 시간이 긴 경우
                moveWidthLimit = MOVE_WIDTH_LIMIT_LONG;  // 이동 거리 늘림
            }
            else {  // 터치 시간이 짧은 경우
                moveWidthLimit = MOVE_WIDTH_LIMIT_SHORT;  // 이동 거리 짧게
            }

            if(move == true) {  // 이동하는 경우
                if (moveRight == true) {  // 오른쪽으로 이동하는 경우
                    if (moveWidth > moveWidthLimit) {   // 이동 거리를 도달했을 경우
                        move = false;  // 이동 멈춤.
                        if(collisionBlock == true) {
                            dx = Metrics.size(R.dimen.jellyking_move_speed);
                            moveRight = true;
                            moveWidth = 0.0f;
                            collisionBlock = false;
                        }
                    }
                }
                else {  // 왼쪽으로 이동하는 경우
                    if (moveWidth < 0) {  // 이동 거리를 도달했을 경우
                        move = false;
                        if(collisionBlock == true) {
                            dx = -(Metrics.size(R.dimen.jellyking_move_speed));
                            moveRight = false;
                            moveWidth = moveWidthLimit;
                            collisionBlock = false;
                        }
                    }
                    if(dx > 0) {
                        dx = -dx;
                    }
                }
            }
            else {
                moveWidth = 0.0f;
            }
        }
        else if(touch == false && collisionStraightLeftBlock == false && collisionStraightRightBlock == false){  // 터치하지 않았을 경우
            dx = 0;  // 이동하지 않음
        }

        dstRect.offset(dx, dy);
        x += dx;
        y += dy;
        moveWidth += dx;

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.jellyking_radius);
        boundingBox.set(x - widthRadius, y - widthRadius, x + widthRadius, y - widthRadius);
        boundingBoxHead.set(x - widthRadius, y - widthRadius, x + widthRadius, y - widthRadius / 2);
        boundingBoxFoot.set(x - widthRadius, y + widthRadius / 2, x + widthRadius, y + widthRadius);
        boundingBoxLeft.set(x - widthRadius, y - widthRadius / 2, x - widthRadius / 2, y + widthRadius / 2);
        boundingBoxRight.set(x + widthRadius / 2, y - widthRadius / 2, x + widthRadius, y + widthRadius / 2);
       }
       ```
   * Blocks 
     * class 정보
       | 종류 | 동작 | 상호작용 |
       |-------------------|-------------------|-------------------|
       | 일반 블록 | | 플레이어 아랫부분과 블록 윗부분 충돌 시, 플레이어 점프 </br> 플레이어 윗부분과 블록 아랫부분 충돌 시, 플레이어 아래로 떨어짐 </br> 플레이어 옆부분과 블록 옆부분 충돌 시, 플레이어 전진 불가 | 
       | 부서지는 블록 | | 플레이어 아랫부분과 블록 윗부분 충돌 시, 플레이어 점프한 후 블록 사라짐 |
       | 전기 블록 | | 플레이어와 충돌 시, 플레이어 사라짐 | 
       | 점프 블록 | | 플레이어 아랫부분과 블록 윗부분 충돌 시, 플레이어 점프 높이 증가 </br> 점프 블록 이미지 변경 | 
       | 이동 블록(좌우, 상하) | 좌우, 상하로 이동 | 플레이어 아랫부분과 블록 윗부분 충돌 시, 플레이어 점프 | 
       | 직진 블록(왼쪽, 오른쪽) |  | 플레이어와 충돌 시, 방향에 따라 플레이어 직진 이동 |  
     * 게임 내에서 class가 책임지는 핵심 코드
       ```
       public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        /* 애니메이션 */
        switch (blockType) {
            case 4:  // JumpBlock
                jumpBlockCollision(frameTime, jumpBlockCollision);
                break;
            case 5:  // MoveLRBlock
                moveLR(frameTime);
                break;
            case 6:  // MoveUDBlock
                moveUD(frameTime);
                break;
        }

        /* boundingBox */
        ...
       }
       ```
   * Enemies 
     * class 정보
       | 종류 | 동작 | 상호작용 |
       |-------------------|-------------------|-------------------|
       | 고정 적 | 지정된 위치에서 돌아가는 애니메이션 실행됨 | 플레이어 아랫부분과 적 윗부분 충돌 시, 플레이어 사망 | 
       | 추락 적 | 일정 시간마다 위에서 아래로 떨어짐 </br> 돌아가는 애니메이션 실행됨 | 플레이어와 충돌 시, 플레이어 사망 |
       | 상하이동 적 | 위아래로 지정된 거리를 이동함 </br> 표정 변하는 애니메이션 실행됨 | 플레이어와 충돌 시, 플레이어 사망 | 
       | 좌우이동 적 | 좌우로 지정된 거리를 이동함 | 플레이어 아랫부분과 적 윗부분 충돌 시, 적 사망 </br> 그 외 플레이어와 충돌 시, 플레이어 사망 | 
     * 게임 내에서 class가 책임지는 핵심 코드
       ```
       public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        /* 애니메이션 */
        if(enemyType != 4) {  // FixEnemy, DropEnemy, MoveUDEnemy
            elapsedTimeForChangeImg += frameTime;
            if (elapsedTimeForChangeImg >= changeImgInterval) {
                bitmap = BitmapPool.get(bitmapId1);
                elapsedTimeForChangeImg -= changeImgInterval;
            } else {
                bitmap = BitmapPool.get(bitmapId2);
            }
        }

        /* 움직임 */
        switch (enemyType) {
            case 2:  // DropEnemy
                drop(frameTime);
                break;
            case 3:  // MoveUDEnemy
                moveUD(frameTime);
                break;
            case 4:  // MoveLREnemy
                moveLR(frameTime);
                break;
        }

        /* boundingBox */
        ...
       }
       ```     
   * Items
   * Star
 * 발표 동영상
   * [발표_동영상_링크] (https://youtu.be/HM-c1UXFpkU)

## 👩‍🏫 최종 발표 : 15주차 목요일 (6/09)
* 게임 소개
  * 공 튀기기 게임
  * 장애물을 피해 이동하면서 별을 모아 스테이지를 클리어하는 게임
  ![jellyking1](https://user-images.githubusercontent.com/65964035/160432688-88a7f5e1-42a1-46b0-989c-f57195af37e4.png)
* 진행 상황
   | 진행 상황 | 내용 | 진행 정도 |
   |-------------------|-------------------|-------------------|
   | 리소스 수집 | 필요한 리소스 모두 수집 완료 | 100% | 
   | 스테이지 기본 틀 구현 | 스테이지 5개 모두 구현 완료 | 100% |
   | 플레이어 캐릭터 이동 구현 | 점프 어색함 | 80% | 
   | 블록 구현 | 충돌 처리 완료 | 100% | 
   | 적 구현 | 일부 적 충돌 처리 오류 | 80% | 
   | 아이템 구현 | 아이템 구현 못함 | 20% |  
   | 별 구현 | 별 구현 완료 | 100% | 
   | 게임 시작 및 종료 구현 | 게임 시작 및 종료 구현 완료 | 100% |
   | 사운드 및 UI 구현 | 사운드 구현 완료, UI 구현 못함 | 50% |  
* git commit
  * 5주차(05/01 일 - 5/07 토)
  * 6주차(05/08 일 - 05/14 토)
  * 7주차(05/15 일 - 04/21 토)   
  ![515528](https://user-images.githubusercontent.com/65964035/172647544-bfcc9e83-da5d-4515-9dcc-91c5f0d23d36.PNG)
  * 8주차(05/22 일 - 05/28 토)
  * 9주차(05/29 일 - 06/04 토)
  ![529604](https://user-images.githubusercontent.com/65964035/172647594-d64d13e3-402f-4de9-bb63-1819f299b515.PNG)
  * 10주차(06/05 일 - 06/09 목)
  ![605](https://user-images.githubusercontent.com/65964035/172686109-c355e9c2-a7a6-49bc-adf8-21be2b82afe8.PNG)
  * 주별 commit수
    | 주차 | commit수 |
    |-------------------|-------------------|
    | 5주차(5/04 - 5/08) | 0 | 
    | 6주차(5/09 - 5/15) | 0 |
    | 7주차(5/16 - 5/22) | 2 | 
    | 8주차(5/23 - 5/29) | 0 | 
    | 9주차(5/30 - 6/05) | 3 |   
    | 10주차(6/6 - 6/09) | 10 |   
* 사용된 기술
  * 캐릭터 점프 - 블록과 충돌 시 점프
  * 터치 길이에 따른 이동 거리 적용
  * 충돌 체크 및 처리 - 플레이어와 블록, 적, 아이템 충돌 체크 및 처리
  * 사운드 처리
* 수업 내용에서 차용한 것
  * 충돌 체크 및 처리
  * 사운드 처리
* 직접 개발한 것
  * 캐릭터 점프
  * 터치 길이에 따른 점프 거리 설정
* 아쉬운 것들
  * 하고 싶었지만 못한 것들
    * 아이템 구현
    * UI 구현
  * (앱을 스토어에 판다면) 팔기 위해 보충할 것들
    * 점프 수정
    * 맵 및 아이템 추가
    * 난이도 조절
    * UI 구현
  * 결국 해결하지 못한 문제/버그
    * 처음 발생하는 사운드가 실행되지 않는 버드
  * 기말 프로젝트를 하면서 겪은 어려움
    * 내가 구현해야할 내용과 수업 진도가 달라 반복해서 수정을 해야하는 것이 어려웠다.
* 수업에 대한 내용
  * 이번 수업에서 기대한 것
    * 스마트폰 게임을 만드는 것
  * 이번 수업에서 얻은 것
    * 스마트폰 게임을 만드는 과정을 이해할 수 있었다.
  * 이번 수업에서 얻지 못한 것
    * 완성된 프로젝트
  * 더 좋은 수업이 되기 위해 변화할 점
    * 코드를 직접 타이핑하고 실행하는 시간이 있었으면 좋을 것 같다.
* 발표 동영상
  * [발표_동영상_링크] (https://youtu.be/QmI1w59uy94) 

