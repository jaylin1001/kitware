<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/Sheader.jsp" %>
	<div>
		<div>
			<div align="right">
				<button class="btn btn-primary">저장</button>
				<button class="btn btn-primary">취소</button>
			</div>
			<div class="table">
				<table class="table table-bordered">
					<tr>
						<th colspan="5">기본 설정</th>
					</tr>
					<tr>
						<th rowspan="2">출퇴근 시간</th>
						<td>출근 시간</td>
						<td colspan="3"><select class="g_in_time1"></select> : 
										<select class="g_in_time2"></select> : 
										<select class="g_in_time3"></select>
						</td>
					</tr>
					<tr>
						<td>퇴근 시간</td>
						<td colspan="3"><select class="g_out_time1"></select> : 
										<select class="g_out_time2"></select> : 
										<select class="g_out_time3"></select>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
<style>
/* body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
} */
/* th {
	text-align: center;
} */

table {
	text-align: center;
}
</style>
<script>
	var className = 'attendance';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
	
	//출근시간1
	$(function(){
		var $intime1Obj = $("select.g_in_time1");
		$intime1Obj.html('<option>선택하세요</option><option>01</option><option>02</option><option>03</option><option>04</option><option>05</option><option>06</option><option>07</option><option>08</option><option>09</option><option>10</option><option>11</option><option>12</option><option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option><option>19</option><option>20</option><option>21</option><option>22</option><option>23</option><option>24</option>'
				);
		$intime1Obj.css('display', 'inline-block');	
	});
	
	//출근시간2
	$(function(){
		var $intime2Obj = $("select.g_in_time2");
		$intime2Obj.html('<option>선택하세요</option><option>1</option><option>2</option>');
		$intime2Obj.css('display', 'inline-block');	
	});
	
	//출근시간3
	$(function(){
		var $intime3Obj = $("select.g_in_time3");
		$intime3Obj.html('<option>선택하세요</option><option>3</option><option>4</option>');
		$intime3Obj.css('display', 'inline-block');	
	});
	
	//퇴근시간1
	$(function(){
		var $outtime1Obj = $("select.g_out_time1");
		$outtime1Obj.html('<option>선택하세요</option><option>01</option><option>02</option><option>03</option><option>04</option><option>05</option><option>06</option><option>07</option><option>08</option><option>09</option><option>10</option><option>11</option><option>12</option><option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option><option>19</option><option>20</option><option>21</option><option>22</option><option>23</option><option>24</option>'
				);
		$outtime1Obj.css('display', 'inline-block');	
	});
	
	//퇴근시간2
	$(function(){
		var $outtime2Obj = $("select.g_out_time2");
		$outtime2Obj.html('<option>선택하세요</option><option>5</option><option>6</option>');
		$outtime2Obj.css('display', 'inline-block');	
	});
	
	//퇴근시간3
	$(function(){
		var $outtime3Obj = $("select.g_out_time3");
		$outtime3Obj.html('<option>선택하세요</option><option>7</option><option>8</option>');
		$outtime3Obj.css('display', 'inline-block');	
	});
</script>

<%@include file="../container/footer.jsp" %>