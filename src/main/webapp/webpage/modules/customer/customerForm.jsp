<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>客户档案管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#collapseOne').collapse('show');
			$('#collapseTwo').collapse('show');
			$('#collapseThree').collapse('show');
			$('#collapseFour').collapse('show');
            $('#createDate').datetimepicker({
                format: "YYYY-MM-DD HH:mm:ss"
            });


		});

		function move(val) {
			if ($("#glyphiconMove" + val).hasClass("glyphicon-chevron-down")) {
				$("#glyphiconMove" + val).removeClass("glyphicon-chevron-down");
				$("#glyphiconMove" + val).addClass("glyphicon-chevron-up");
				$("#show_text" + val).text("收起");
			} else {
				$("#glyphiconMove" + val).removeClass("glyphicon-chevron-up");
				$("#glyphiconMove" + val).addClass("glyphicon-chevron-down");
				$("#show_text" + val).text("展开");
			}
		}

		function save() {
            var isValidate = jp.validateForm('#inputForm');//校验表单
            if(!isValidate){
                return false;
			}else{
                jp.loading();
                jp.post("${ctx}/customer/customer/save",$('#inputForm').serialize(),function(data){
                    if(data.success){
                        jp.getParent().refresh();
                        var dialogIndex = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(dialogIndex);
                        jp.success(data.msg)

                    }else{
                        jp.error(data.msg);
                    }
                })
			}

        }
	</script>
	<script type="text/javascript">
        $(function () {
            $("#tcontactId").change(function(){

				var mobile= $(this).find('option:selected').attr('mobile');
				$("input[name='mobile']").val(mobile);
            });
        });

	</script>
</head>
<body class="bg-white">
<form:form id="inputForm" modelAttribute="customer" class="form-horizontal">
<form:hidden path="id"/>
	<div class="panel-group" id="accordion">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title">
					基础信息
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" onclick="move('One');">
						<span id="show_textOne" style="float: right;">收起</span>
						<span id="glyphiconMoveOne" class="glyphicon glyphicon-chevron-up" style="float: right;"></span>
					</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body">
					<table class="table table-bordered">
						<tbody>
						<tr>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>客户名称：</label></td>
							<td class="width-35">
								<form:input path="name" htmlEscape="false"    class="form-control required"/>
							</td>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>客户代码：</label></td>
							<td class="width-35">
								<form:input path="number" htmlEscape="false"  readonly="true"  class="form-control required"/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属公司：</label></td>
							<td class="width-35">
								<form:input path="orgId" htmlEscape="false"  class="form-control required"/><%--readonly="true"--%>
							</td>
							<td class="width-15 active"><label class="pull-right">销售人员：</label></td>
							<td class="width-35">
								<%--<form:input path="empId" htmlEscape="false"    class="form-control "/>--%>
                                <form:input  path="empId" id="empId" name="empId" htmlEscape="false" class="form-control " style="background: url(${ctxStatic}/common/images/user.png) no-repeat; background-position: left; background-size: 25px 25px; cursor: pointer; padding-left: 25px;"/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">资料状态：</label></td>
							<td class="width-35">
								<%--<form:input path="state" htmlEscape="false"    class="form-control "/>--%>
								<form:radiobutton path="state" value="1" class="i-checks " checked="${checked}"/>使用
								<form:radiobutton path="state" value="0" class="i-checks "/>停用
							</td>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>客户来源：</label></td>
							<td class="width-35">
								<%--<form:input path="sourceId" htmlEscape="false"    class="form-control required"/>--%>
								<form:select path="sourceId" class="form-control required">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('customer_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>跟进状态：</label></td>
							<td class="width-35">
								<form:select path="statusId" class="form-control required">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('follow_up_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属区域：</label></td>
							<td class="width-35">
								<%--<form:input path="areaId" htmlEscape="false"    class="form-control required"/>--%>
								<form:select path="areaId" class="form-control required">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('respective_region')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">客户地址：</label></td>
							<td class="width-35" >
								<form:input path="address" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">归属部门：</label></td>
							<td class="width-35">
								<sys:treeselect id="office" name="office.id" value="${customer.office.id}" labelName="office.name" labelValue="${customer.office.name}"
												title="部门" url="/sys/office/treeData?type=2" cssClass="form-control required" allowClear="true" notAllowSelectParent="true"/>
							</td>
						</tr>


						<c:if test="${customer.createBy.id!=null&& customer.createBy.id!=''}">
							<tr>
								<td class="width-15 active"><label class="pull-right">创建人：</label></td>
								<td class="width-35">
									<input type="text" value="${customer.createBy.name}" htmlEscape="false" class="form-control " readonly="true"/>
									<form:input path="createBy.id" type="hidden" htmlEscape="false" class="form-control " readonly="true"/>
								</td>
								<td class="width-15 active"><label class="pull-right">创建时间：</label></td>
								<td class="width-35">
									<fmt:formatDate value="${customer.createDate}" pattern="yyyy-MM-dd HH:mm:ss" var="time"/>
									<input value="${time}" htmlEscape="false" class="form-control " readonly="true"/>
										<%--<div class='input-group form_datetime' id='createDate'>
                                            <input type='text'  name="createDate" class="form-control required"  value="<fmt:formatDate value="${传.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                            <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                        </div>--%>
								</td>
							</tr>
						</c:if>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title">
					客户详细
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" onclick="move('Two');">
						<span id="show_textTwo" style="float: right;">收起</span>
						<span id="glyphiconMoveTwo" class="glyphicon glyphicon-chevron-up" style="float: right;"></span>
					</a>
				</h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse in">
				<div class="panel-body">
					<table class="table table-bordered">
						<tbody data-toggle="distpicker">
						<tr>
							<td class="width-15 active"><label class="pull-right">企业法人：</label></td>
							<td class="width-35">
								<form:input path="legalPerson" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">企业曾用名：</label></td>
							<td class="width-35">
								<form:input path="usedName" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>主联系人：</label></td>
							<td class="width-35">
								<select name="contacts.id" id="tcontactId"  class="form-control required">
									<c:if test="${customer.contacts.id!=null}">
										<option value="${customer.contacts.id}"  mobile="${customer.contacts.mobile}" name="" htmlEscape="false">${customer.contacts.name}</option>
									</c:if>
									<c:if test="${customer.contacts.id==null}">
										<option value="" name="" htmlEscape="false">暂无主联系人请选择</option>
									</c:if>
									<c:forEach items="${findList}" var="item">
										<option value="${item.id}" name="" mobile="${item.mobile}" htmlEscape="false">${item.name}</option>
									</c:forEach>
								</select>
								<%--<marketing:contactsSelect id="tcontactId" name="contacts.id" value="${customer.contacts.id}" labelName="contacts.name" labelValue="${customer.contacts.name}"
														  fieldLabels="联系人姓名|联系方式|所在部门|职务|办公室号码" fieldKeys="name|mobile|department|title|officePhone" searchLabels="联系人姓名|所在部门|职务"
														  searchKeys="name|department|title" title="选择所属公司" url="${ctx}/contacts/contacts/data?id=${customer.id}" cssClass="form-control "/>--%>



							</td>
							<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
							<td class="width-35">
								<input name="mobile" value="${customer.contacts.mobile}" htmlEscape="false" class="form-control"/>
							</td>
						</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>省份：</label></td>
								<td class="width-35" >
									<select name="provincelId" id="province2" data-province="---- 选择省 ----" htmlEscape="false" class="form-control requireds">
										<option  value="${customer.provincelId}" <c:if test="${customer.provincelId!=null&&customer.provincelId!='' }">selected="selected"</c:if> >${customer.provincelId}</option>
									</select>

									<%--<form:input path="provincelId" htmlEscape="false"    class="form-control required"/>--%>
								</td>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>城市：</label></td>
								<td class="width-35">
									<select name="cityId" id="city2" data-city="---- 选择市 ----"  htmlEscape="false" class="form-control required"></select>
									<%--<form:input path="cityId" htmlEscape="false"    class="form-control required"/>--%>
								</td>
							</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>区县：</label></td>
								<td class="width-35">
									<select  name="countryId" id="district2" data-district="---- 选择区 ----" htmlEscape="false" class="form-control required"></select>
										<%--<form:input path="countryId" htmlEscape="false"    class="form-control required"/>--%>
								</td>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>镇街：</label></td>
								<td class="width-35">
									<%--<select  name="streetId" id="street2" data-district="---- 选择街 ----" htmlEscape="false" class="form-control required"></select>--%>
									<form:input path="streetId" htmlEscape="false"    class="form-control required"/>
								</td>
							</tr>

						<tr>
							<td class="width-15 active"><label class="pull-right">详细地址：</label></td>
							<td class="width-35">
								<form:input path="detailAddress" htmlEscape="false"  class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">GPS定位：</label></td>
							<td class="width-35">
								<form:input path="gps" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">客户简称：</label></td>
							<td class="width-35">
								<form:input path="shortName" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属行业：</label></td>
							<td class="width-35">
								<form:select path="industoryId" class="form-control required">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('sys_industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">客户等级：</label></td>
							<td class="width-35">
								<form:select path="cusLevel" class="form-control ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('customer_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
							<td class="width-15 active"><label class="pull-right">官网地址：</label></td>
							<td class="width-35">
								<form:input path="webSite" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">传真号码：</label></td>
							<td class="width-35">
								<form:input path="fax" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">官方邮箱：</label></td>
							<td class="width-35">
								<form:input path="email" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>企业性质：</label></td>
							<td class="width-35">
								<form:input path="propertyId" htmlEscape="false"    class="form-control required"/>
							</td>
							<td class="width-15 active"><label class="pull-right">统一信用代码：</label></td>
							<td class="width-35">
								<form:input path="creditCode" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">银行账号：</label></td>
							<td class="width-35">
								<form:input path="account" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">开户行：</label></td>
							<td class="width-35">
								<form:input path="bank" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">经营范围：</label></td>
							<td class="width-35">
								<form:input path="business" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>是否公海：</label></td>
							<td class="width-35">
								<%--<form:input path="isPublic" htmlEscape="false"    class="form-control required"/>--%>
									<form:radiobutton path="isPublic" value="1" class="i-checks " checked="${checked}"/>是
									<form:radiobutton path="isPublic" value="0" class="i-checks "/>否
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
							<td class="width-35" colspan="3">
								<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title">
					经营状况
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" onclick="move('Three');">
						<span id="show_textThree" style="float: right;">收起</span>
						<span id="glyphiconMoveThree" class="glyphicon glyphicon-chevron-up" style="float: right;"></span>
					</a>
				</h4>
			</div>
			<div id="collapseThree" class="panel-collapse collapse in">
				<div class="panel-body">
					<table class="table table-bordered">
						<tbody>
						<tr>
							<td class="width-15 active"><label class="pull-right">竞争对手：</label></td>
							<td class="width-35">
								<form:input path="competitor" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">核心优势：</label></td>
							<td class="width-35">
								<form:input path="coreAdvantage" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">行业地位：</label></td>
							<td class="width-35">
								<form:input path="industryPosition" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">行业标准：</label></td>
							<td class="width-35">
								<form:input path="industryStandard" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">年营业额：</label></td>
							<td class="width-35">
								<form:input path="turnoverYearly" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">年纳税额：</label></td>
							<td class="width-35">
								<form:input path="taxYearly" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">经营近况：</label></td>
							<td class="width-35" colspan="3">
								<form:input path="recent" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title">
					科研资源与成果
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseFour" onclick="move('Four');">
						<span id="show_textFour" style="float: right;">收起</span>
						<span id="glyphiconMoveFour" class="glyphicon glyphicon-chevron-up" style="float: right;"></span>
					</a>
				</h4>
			</div>
			<div id="collapseFour" class="panel-collapse collapse in">
				<div class="panel-body">
					<table class="table table-bordered">
						<tbody>
						<tr>
							<td class="width-15 active"><label class="pull-right">职工总人数：</label></td>
							<td class="width-35">
								<form:input path="workerNum" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">科研人员人数：</label></td>
							<td class="width-35">
								<form:input path="researcherNum" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">科研人员占比：</label></td>
							<td class="width-35">
								<form:input path="rateA" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">大专科研人数：</label></td>
							<td class="width-35">
								<form:input path="juniorNum" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">本科科研人数：</label></td>
							<td class="width-35">
								<form:input path="undergraduateNum" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">硕士科研人数：</label></td>
							<td class="width-35">
								<form:input path="masterNum" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">初职科研人数：</label></td>
							<td class="width-35">
								<form:input path="primaryNum" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">中职科研人数：</label></td>
							<td class="width-35">
								<form:input path="intermediateNum" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">知识产权经费：</label></td>
							<td class="width-35">
								<form:input path="intellectualFunds" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">经费占比：</label></td>
							<td class="width-35">
								<form:input path="rateB" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">发明专利数量：</label></td>
							<td class="width-35">
								<form:input path="inventionPatentNum" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">外观专利数量：</label></td>
							<td class="width-35">
								<form:input path="appearancePatentNum" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">商标版权数量：</label></td>
							<td class="width-35">
								<form:input path="trademarkNum" htmlEscape="false"    class="form-control "/>
							</td>
							<td class="width-15 active"><label class="pull-right">实用型专利数量：</label></td>
							<td class="width-35">
								<form:input path="practicalPatentNum" htmlEscape="false"    class="form-control "/>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

		<%--<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">ERP系统客户Id：</label></td>
					<td class="width-35">
						<form:input path="erpcusId" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门Id：</label></td>
					<td class="width-35">
						<form:input path="deptId" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>是否公海：</label></td>
					<td class="width-35">
						<form:input path="isPublic" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
		 	</tbody>
		</table>--%>
	</form:form>
<!-- 引入地址 begin -->
<script src="${ctxStatic}/plugin/dist/js/bootstrap.min.js"></script>
<script src="${ctxStatic}/plugin/dist/js/distpicker.data.js"></script>
<script src="${ctxStatic}/plugin/dist/js/distpicker.js"></script>
<script src="${ctxStatic}/plugin/dist/js/main.js"></script>
<!-- 引入地址 end -->
</body>
</html>