# [Silver III] 전공책 - 16508 

[문제 링크](https://www.acmicpc.net/problem/16508) 

### 성능 요약

메모리: 18600 KB, 시간: 628 ms

### 분류

비트마스킹, 브루트포스 알고리즘

### 제출 일자

2024년 8월 19일 00:58:45

### 문제 설명

<p>곧 졸업을 앞둔 민호는 대학교 생활 동안 구매만 해놓고 한 번도 펴보지 않은 전공책에 먼지가 쌓여 있는 것을 보고는, 이 책들을 어떻게 처리해야 할지 고민 중이다. 열심히 고민한 끝에 민호는 결국 전공책을 모두 버리기로 마음먹었다. 하지만 그냥 버리기엔 심심한 민호는 전공책 제목에 있는 글자들을 오려서 단어 만들기 놀이를 하려고 한다. 단어 만들기 놀이는 아래 예시와 같다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/93ada309-dbba-4cee-831f-5e977b42e526/-/preview/" style="height: 288px; width: 256px;"><img alt="" src="https://upload.acmicpc.net/22501cf0-f7ad-40b7-a94f-aeda4f64fefb/-/preview/" style="height: 288px; width: 256px;"><img alt="" src="https://upload.acmicpc.net/99d2c4ba-0c42-4333-a8d8-6a0600f94119/-/preview/" style="height: 288px; width: 256px;"><img alt="" src="https://upload.acmicpc.net/bd7a7845-0cce-427b-a000-cdde0af7d960/-/preview/" style="height: 288px; width: 256px;"></p>

<ul>
	<li>1번 책 : <code>COMPUTERARCHITECTURE</code> (35,000원)</li>
	<li>2번 책 : <code>ALGORITHM</code> (47,000원)</li>
	<li>3번 책 : <code>NETWORK</code> (43,000원)</li>
	<li>4번 책 : <code>OPERATINGSYSTEM</code> (40,000원)</li>
</ul>

<p>만약 민호가 만들고 싶은 단어가 <code>ALMIGHTY</code>라고 하면, 위 4개의 책 중, 1번 책에서 <code>A</code>를, 2번 책에서 <code>L, M, I, G, H, T</code>를, 4번 책에서 <code>Y</code>를 오려내어 원하는 단어를 만들 수 있다. 이때 드는 비용은 1번, 2번, 4번 책 가격의 합인 <code>122,000원</code>이다.</p>

<p>만약 <code>ANT</code>라는 단어를 만들고 싶다고 하면, 2번 책에서 <code>A</code>를, 3번 책에서 <code>N, T</code>를 오려내어 원하는 단어를 만들 수 있다. 이때 드는 비용은 2번과 3번 책 가격을 합하여 <code>90,000원</code>이다. 그런데, <code>ANT</code>라는 단어에서 <code>A</code>를 2번 책에서 오려내지 않고, 4번 책에 있는 <code>A</code>를 오려낼 수도 있다. 만약 4번 책에서 <code>A</code>를 오려냈을 때 드는 비용은 3번과 4번 책 가격을 합하여 <code>83,000원</code>으로 2번과 3번 책을 고른 비용보다 작다. 하지만, 4번 책에는 <code>ANT</code>가 모두 포함되어 있으므로, 4번 책만 선택했을 때 드는 비용이 <code>40,000원</code>이다. 이는 <code>ANT</code>라는 단어를 만들기 위해서 가장 적은 비용이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/9a1b6d04-a970-4924-a1df-bd53b5b27993/-/preview/" style="height: 297px; width: 500px;"></p>

<p>민호는 여러 개의 전공책들을 나열해 놓고는, 심각한 고민 끝에 전공책 제목에 있는 글자를 오려내어 자신이 원하는 단어를 만들기 위해서는 여러 가지 경우가 있다는 것을 깨달았다. 매우 심심했던 민호는 가장 적은 비용으로 자신이 원하는 단어를 만들려면 어떤 전공책들을 선택해야 하는지 궁금했다. 하지만 일일이 가능한 조합을 만들어 보는 것은 매우 시간 낭비라고 생각한 민호는 컴퓨터공학과답게 프로그램을 만들려고 한다.</p>

<p>민호를 도와 각 전공책의 가격과 제목이 주어졌을 때, 가장 적은 비용으로 민호가 원하는 단어를 만들기 위해서 어떤 전공책을 선택해야 하는지 알아보자.</p>

### 입력 

 <p>첫 번째 줄에는 민호가 만들고자 하는 단어를 의미하는 문자열 <em>T</em> (1 ≤ |<em>T</em>| ≤ 10)가 주어진다. <em>T</em>는 항상 대문자이며, |<em>T</em>|는 문자열 <em>T</em>의 길이를 의미한다.</p>

<p>두 번째 줄에는 민호가 가진 전공책의 개수를 의미하는 정수 <em>N</em> (1 ≤ <em>N</em> ≤ 16)이 주어진다.</p>

<p>다음 <em>N</em>개의 각 줄에는 전공책 가격을 의미하는 정수 <em>C<sub>i</sub></em> (10,000 ≤ <em>C<sub>i</sub></em> ≤ 100,000)와 제목을 의미하는 문자열 <em>W<sub>i</sub></em> (1 ≤ |<em>W<sub>i</sub></em>| ≤ 50)가 주어진다. <em>W<sub>i</sub></em>는 항상 대문자이다.</p>

### 출력 

 <p>민호가 원하는 단어 <em>T</em>를 만들기 위해서 선택해야 하는 전공책의 가장 적은 가격의 합을 출력한다. 만약 단어를 만들 수 없다면 <code>-1</code>을 출력한다.</p>

