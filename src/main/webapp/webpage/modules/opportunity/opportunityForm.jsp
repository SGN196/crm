<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>线索商机管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
            $("textarea").css("resize", "none");
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
                jp.post("${ctx}/opportunity/opportunity/save",$('#inputForm').serialize(),function(data){
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
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="opportunity" class="form-horizontal">
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
									<td class="width-15 active"><label class="pull-right">商机编号：</label></td>
									<td class="width-35">
										<form:input path="oppNo" htmlEscape="false"  readonly="true"  class="form-control "/>
									</td>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机名称：</label></td>
									<td class="width-35">
										<form:input path="oppName" htmlEscape="false"    class="form-control required"/>
									</td>
								</tr>
								<tr>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>客户名称：</label></td>
									<td class="width-35">
										<%--<form:input path="custId" htmlEscape="false"    class="form-control required"/>--%>
											<marketing:customerSelect id="tcusId" name="customer.id" value="${opportunity.customer.id}" labelName="customer.name" labelValue="${opportunity.customer.name}"
																	  fieldLabels="客户名称|客户法人|跟进状态" fieldKeys="name|legalPerson|statusId" searchLabels="客户名称|客户法人|跟进状态"
																	  searchKeys="name|legalPerson|statusId" title="选择所属公司" url="${ctx}/customer/customer/data" cssClass="form-control "/>
									</td>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机来源编号：</label></td>
									<td class="width-35">
										<form:select path="sourceId" class="form-control required">
											<form:option value="" label=""/>
											<form:options items="${fns:getDictList('customer_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
										</form:select>
									</td>

								</tr>
								<tr>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机状态：</label></td>
									<td class="width-35">
										<%--<form:input path="status" htmlEscape="false"    class="form-control required"/>--%>
										<form:select path="status" class="form-control required">
											<form:option value="" label=""/>
											<form:options items="${fns:getDictList('business_opportunity_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
										</form:select>
									</td>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属公司编号：</label></td>
									<td class="width-35">
										<sys:treeselect id="company" name="company.id" value="${opportunity.company.id}" labelName="company.name" labelValue="${opportunity.company.name}"
														title="公司" url="/sys/office/treeData?type=1" allowClear="true" cssClass="form-control required"/>
										<%--<form:input path="orgId" htmlEscape="false"    class="form-control required"/>--%>
									</td>
								</tr>
								<tr>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门编号：</label></td>
									<td class="width-35">
										<%--<form:input path="deptId" htmlEscape="false"    class="form-control required"/>--%>
											<sys:treeselect id="office" name="office.id" value="${opportunity.office.id}" labelName="office.name" labelValue="${opportunity.office.name}"
															allowClear="true" title="部门" url="/sys/office/treeData?type=2" cssClass="form-control required" notAllowSelectParent="true"/>
									</td>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>跟进人员编号：</label></td>
									<td class="width-35">
										<form:input path="empId" htmlEscape="false"    class="form-control required"/>
									</td>
								</tr>
								<tr>
									<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机阶段编号：</label></td>
									<td class="width-35">
										<%--<form:input path="oppStageId" htmlEscape="false"    class="form-control required"/>--%>
											<form:select path="oppStageId" class="form-control required">
												<form:option value="" label=""/>
												<form:options items="${fns:getDictList('business_opportunity_stage')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
											</form:select>
									</td>
									<td class="width-15 active"><label class="pull-right">最近跟进：</label></td>
									<td class="width-35">
										<%--<form:input path="" htmlEscape="false"    class="form-control required isFloatGteZero"/>--%>
										<input type="text"  htmlEscape="false"    class="form-control"/>
									</td>
								</tr>
								<c:if test="${opportunity.createBy.id!=null&& opportunity.createBy.id!=''}">
									<tr>
										<td class="width-15 active"><label class="pull-right">创建人：</label></td>
										<td class="width-35">
											<input type="text" value="${opportunity.createBy.name}" htmlEscape="false"  class="form-control " readonly="true">
											<form:input path="createBy.id" type="hidden" htmlEscape="false"  class="form-control " readonly="true"/>
										</td>
										<td class="width-15 active"><label class="pull-right">创建时间：</label></td>
										<td class="width-35">
											<fmt:formatDate value="${opportunity.createDate}" pattern="yyyy-MM-dd HH:mm:ss" var="time"/>
											<input value="${time}" htmlEscape="false" class="form-control " readonly="true"/>

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
						商机要素
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" onclick="move('Two');">
							<span id="show_textTwo" style="float: right;">收起</span>
							<span id="glyphiconMoveTwo" class="glyphicon glyphicon-chevron-up" style="float: right;"></span>
						</a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse in">
					<div class="panel-body">
						<table class="table table-bordered">
							<tbody>
							<tr>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>资金预算：</label></td>
								<td class="width-35">
									<form:input path="budget" htmlEscape="false"    class="form-control required isFloatGteZero"/>
								</td>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>是否立项：</label></td>
								<td class="width-35">
									<form:radiobutton path="commit" value="1" class="i-checks " checked="${checked}"/>是
									<form:radiobutton path="commit" value="0" class="i-checks "/>否
								</td>

							</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>决策人：</label></td>
								<td class="width-35">
									<form:input path="decisionmaker" htmlEscape="false"    class="form-control required"/>
								</td>
								<td class="width-15 active"><label class="pull-right">预计成交日期：</label></td>
								<td class="width-35">
									<form:input path="planDate" htmlEscape="false"    class="form-control "/>
								</td>
							</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right">决策流程：</label></td>
								<td class="width-35">
									<form:textarea path="process" htmlEscape="false" rows="4"    class="form-control "/>
								</td>
								<td class="width-15 active"><label class="pull-right">决策因素：</label></td>
								<td class="width-35">
									<form:textarea path="factors" htmlEscape="false" rows="4"    class="form-control "/>
								</td>
							</tr>
							<tr>
								<td class="width-15 active"><label class="pull-right">竞争对手：</label></td>
								<td class="width-35">
									<form:input path="competitor" htmlEscape="false"    class="form-control "/>
								</td>
								<%--<td class="width-15 active"><label class="pull-right">上次跟进时间：</label></td>
								<td class="width-35">
									<form:input path="lastTime" htmlEscape="false"    class="form-control "/>
								</td>--%>
								<td class="width-15 active"><label class="pull-right"><font color="red">*</font>延时申请次数：</label></td>
								<td class="width-35">
									<form:input path="deayTimes" htmlEscape="false"    class="form-control required"/>
								</td>
							</tr>
							<tr>

								<td class="width-15 active"><label class="pull-right">需求描述：</label></td>
								<td class="width-35" colspan="3">
									<form:textarea path="demand" htmlEscape="false" rows="4"    class="form-control "/>
								</td>
							</tr>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>

	</form:form>
</body>
</html>