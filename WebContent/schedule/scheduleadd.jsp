<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal-body">
		<form id="addsc">
		<table>
			<tr>
				<td><label>제목 </label></td>
				<td><input style="width: 300px" required="required" name="title" autocomplete="off" id="schtitle"></td>
			</tr>
			<tr>
				<td><label>일정종류</label></td>
				<td><select class="sp" name="schtype" id="schtype">
						<option>업무</option>
						<option>회의</option>
						<option>외근</option>
						<option>출장</option>
						<option>교육</option>
						<option>워크샵</option>
						<option>미팅</option>
						<option>휴가</option>
						<option>기타</option>
				</select></td>
			</tr>
			<tr>
				<td><label>START</label></td>
				<td><input type="text" class="date" name="startdate" id="testDatepicker"
					value="시작일"> <select name="starthour" id="starthour">
						<option>00</option>
						<option>01</option>
						<option>02</option>
						<option>03</option>
						<option>04</option>
						<option>05</option>
						<option>06</option>
						<option>07</option>
						<option>08</option>
						<option>09</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
						<option>13</option>
						<option>14</option>
						<option>15</option>
						<option>16</option>
						<option>17</option>
						<option>18</option>
						<option>19</option>
						<option>20</option>
						<option>21</option>
						<option>22</option>
						<option>23</option>
				</select> <label>시</label> <select name="startminute" id="startmin">
						<option>00</option>
						<option>01</option>
						<option>02</option>
						<option>03</option>
						<option>04</option>
						<option>05</option>
						<option>06</option>
						<option>07</option>
						<option>08</option>
						<option>09</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
						<option>13</option>
						<option>14</option>
						<option>15</option>
						<option>16</option>
						<option>17</option>
						<option>18</option>
						<option>19</option>
						<option>20</option>
						<option>21</option>
						<option>22</option>
						<option>23</option>
						<option>24</option>
						<option>25</option>
						<option>26</option>
						<option>27</option>
						<option>28</option>
						<option>29</option>
						<option>30</option>
						<option>31</option>
						<option>32</option>
						<option>33</option>
						<option>34</option>
						<option>35</option>
						<option>36</option>
						<option>37</option>
						<option>38</option>
						<option>39</option>
						<option>40</option>
						<option>41</option>
						<option>42</option>
						<option>43</option>
						<option>44</option>
						<option>45</option>
						<option>46</option>
						<option>47</option>
						<option>48</option>
						<option>49</option>
						<option>50</option>
						<option>51</option>
						<option>52</option>
						<option>53</option>
						<option>54</option>
						<option>55</option>
						<option>56</option>
						<option>57</option>
						<option>58</option>
						<option>59</option>
				</select> <label>분 </label>&nbsp;&nbsp;&nbsp; <label>하루종일</label> <input
					type="checkbox" value="하루종일"></td>

			</tr>
			<tr id="repeatok2">
				<td><label>END</label></td>
				<td><input type="text" class="date" name="enddate" id="testDatepicker2"
					value="종료일"> <select name="endhour" id="endhour" >
						<option>00</option>
						<option>01</option>
						<option>02</option>
						<option>03</option>
						<option>04</option>
						<option>05</option>
						<option>06</option>
						<option>07</option>
						<option>08</option>
						<option>09</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
						<option>13</option>
						<option>14</option>
						<option>15</option>
						<option>16</option>
						<option>17</option>
						<option>18</option>
						<option>19</option>
						<option>20</option>
						<option>21</option>
						<option>22</option>
						<option>23</option>
				</select> <label>시</label> <select name="endminute" id="endmin">
						<option>00</option>
						<option>01</option>
						<option>02</option>
						<option>03</option>
						<option>04</option>
						<option>05</option>
						<option>06</option>
						<option>07</option>
						<option>08</option>
						<option>09</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
						<option>13</option>
						<option>14</option>
						<option>15</option>
						<option>16</option>
						<option>17</option>
						<option>18</option>
						<option>19</option>
						<option>20</option>
						<option>21</option>
						<option>22</option>
						<option>23</option>
						<option>24</option>
						<option>25</option>
						<option>26</option>
						<option>27</option>
						<option>28</option>
						<option>29</option>
						<option>30</option>
						<option>31</option>
						<option>32</option>
						<option>33</option>
						<option>34</option>
						<option>35</option>
						<option>36</option>
						<option>37</option>
						<option>38</option>
						<option>39</option>
						<option>40</option>
						<option>41</option>
						<option>42</option>
						<option>43</option>
						<option>44</option>
						<option>45</option>
						<option>46</option>
						<option>47</option>
						<option>48</option>
						<option>49</option>
						<option>50</option>
						<option>51</option>
						<option>52</option>
						<option>53</option>
						<option>54</option>
						<option>55</option>
						<option>56</option>
						<option>57</option>
						<option>58</option>
						<option>59</option>
				</select> <label>분</label></td>
			</tr>
			<tr style="display:none">
				<td><label>반복여부</label></td>
				<td><input type="radio" name="repeatbl" value="N"
					checked="checked">비반복 <input type="radio" name="repeatbl"
					value="Y">반복</td>
			</tr>

			<tr style="display: none" id="repeatok">
				<td><label>반복주기</label></td>
				
				<td>
				<input style="width: 40px;display:none" name="dayinput" id="dayinput" >
				<label>주</label>
				<input type="radio" name="repeatterm" value="1">
				<label>월</label>				
				<input type="radio"	name="repeatterm" value="2">
				<label>년</label>
				<input type="radio" name="repeatterm" value="3">
				<span style="display:none;padding: 5px;background-color:#5882FA" id="weekcheck" >
				<input	type="checkbox" name="week" id="week1" value="1"><label for="week1">월</label>
				<input	type="checkbox" name="week" id="week2" value="2"><label for="week2">화</label>
				<input	type="checkbox" name="week" id="week3" value="3"><label for="week3">수</label>
				<input	type="checkbox" name="week" id="week4" value="4"><label for="week4">목</label>
				<input	type="checkbox" name="week" id="week5" value="5"><label for="week5">금</label>
				<input	type="checkbox" name="week" id="week6" value="6"><label for="week6">토</label>
				<input	type="checkbox" name="week" id="week7" value="7"><label for="week7">일</label>
				</span>

			</tr>
			<tr>
				<td><label>내용</label></td>
				<td><textarea autocomplete="off" name="contents" rows="6" cols="50" id="contents"></textarea></td>
				<td><input id="code" type="hidden" name="schcode" ></td>
				<td><input id="schno" type="hidden" name="schno" ></td>
			</tr>
		</table>
		<div class="modal-footer">
			
			<input type="button" name = "submitbutton" value="추가" class="btn btn-default addschedule" >
			<input type="button" name = "submitbutton" value="수정"  class="btn btn-default editschedule">
			<input type="button" name = "submitbutton" value="삭제"  class="btn btn-default delschedule">
			<button type="button" class="btn btn-default closebutton" data-dismiss="modal">닫기</button>
		</div>
		</form>
		</div>
		