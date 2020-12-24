<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
  #toc-content {
    display: none;
  }
  #toc-toggle {
    cursor: pointer;
    color: #2962ff;
  }
  #toc-toggle:hover {
    text-decoration: underline;
  }
  .answer {
    display: none;
    padding-bottom: 30px;
  }
  #faq-title {
    font-size: 25px;
  }
  .faq-content {
    border-bottom: 1px solid #e0e0e0;
  }
  .question {
    font-size: 19px;
    padding: 30px 0;
    cursor: pointer;
    border: none;
    outline: none;
    background: none;
    width: 100%;
    text-align: left;
  }
  .question:hover {
    color: #2962ff;
  }
  [id$="-toggle"] {
    margin-right: 15px;
  }
</style>
자주묻는질문 [<span id="toc-toggle" onclick="openCloseToc()">보이기</span>]
<ol id="toc-content">
  <li><span id="faq-title">자주 묻는 질문(FAQ)</span>
<div class="faq-content">
  <button class="question" id="que-1"><span id="que-1-toggle">+</span><span>배송이 얼마나 걸리나요?</span></button>
  <div class="answer" id="ans-1">" 재고 있는 품목은 오후 12시 이전 단독 주문 시 당일 배송 가능 "



재고가 있는 품목은 오후 12시 이전에 단독으로 주문하는 경우 당일 배송이 가능합니다.

단, 거래처 주문 상품의 경우 상품 준비 기간이 소요되며, 예약 주문 상품은 상품 페이지 상단에서 입고 일정 확인이 가능합니다.



※ 지연 상품이 포함된 주문 건 및 이벤트 주문 건은 배송이 늦어질 수 있는 점 참고 바랍니다.</div>
</div>
</li>
  <li><div class="faq-content">
  <button class="question" id="que-2"><span id="que-2-toggle">+</span><span>무료반품안내</span></button>
  <div class="answer" id="ans-2">취소 신청한 상품이 발송되는 경우는 이미 송장번호가 출력되어 물류 센터에서 출고 진행 중인 상황입니다.

이 경우 택배 기사 방문 시 상품을 수령하지 않겠다는 의사를 밝혀주시면 수취 거부로 반송되며 반입 시 무료 반품 가능합니다. 다만, 부분 반품 시 무료 배송 조건을 충족하지 않는 경우 반입 배송비만 무료로 진행되며, 최초 배송비는 차감되는 점 참고 부탁드립니다.</div>
</div></li>
  <li><div class="faq-content">
  <button class="question" id="que-3"><span id="que-3-toggle">+</span><span>취소주문안내</span></button>
  <div class="answer" id="ans-3">마이페이지 주문 내역에서 취소 신청해 주시면 담당자가 배송 여부 확인 후 순차 환불 처리합니다.



단, 이미 송장번호가 출력되어 물류 센터에서 출고 진행 중인 경우 취소 거부될 수 있는 점 참고 부탁드립니다. 이 경우 택배 기사 방문 시 상품을 수령하지 않겠다는 의사를 밝혀주시면 수취 거부로 반송되며 반입 시 무료 반품 가능합니다. 다만, 부분 반품 시 무료 배송 조건을 충족하지 않는 경우 반입 배송비만 무료로 진행되며, 최초 배송비는 차감되는 점 참고 부탁드립니다.</div>
</div>
</li>
</ol>



<script>
function openCloseToc() {
    if(document.getElementById('toc-content').style.display === 'block') {
      document.getElementById('toc-content').style.display = 'none';
      document.getElementById('toc-toggle').textContent = '보이기';
    } else {
      document.getElementById('toc-content').style.display = 'block';
      document.getElementById('toc-toggle').textContent = '숨기기';
    }
  }
  const items = document.querySelectorAll('.question');

  function openCloseAnswer() {
    const answerId = this.id.replace('que', 'ans');

    if(document.getElementById(answerId).style.display === 'block') {
      document.getElementById(answerId).style.display = 'none';
      document.getElementById(this.id + '-toggle').textContent = '+';
    } else {
      document.getElementById(answerId).style.display = 'block';
      document.getElementById(this.id + '-toggle').textContent = '-';
    }
  }

  items.forEach(item => item.addEventListener('click', openCloseAnswer));
</script>
</body>
</html>