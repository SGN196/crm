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
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">商机编号：</label></td>
					<td class="width-35">
						<form:input path="oppNo" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机名称：</label></td>
					<td class="width-35">
						<form:input path="oppName" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机来源编号：</label></td>
					<td class="width-35">
						<form:input path="sourceId" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>客户编号：</label></td>
					<td class="width-35">
						<form:input path="custId" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机状态：</label></td>
					<td class="width-35">
						<form:input path="status" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属公司编号：</label></td>
					<td class="width-35">
						<form:input path="orgId" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门编号：</label></td>
					<td class="width-35">
						<form:input path="deptId" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>跟进人员编号：</label></td>
					<td class="width-35">
						<form:input path="empId" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机阶段编号：</label></td>
					<td class="width-35">
						<form:input path="oppStageId" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>资金预算：</label></td>
					<td class="width-35">
						<form:input path="budget" htmlEscape="false"    class="form-control required isFloatGteZero"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>是否立项：</label></td>
					<td class="width-35">
						<form:input path="commit" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">需求描述：</label></td>
					<td class="width-35">
						<form:textarea path="demand" htmlEscape="false" rows="4"    class="form-control "/>
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
					<td class="width-15 active"><label class="pull-right">上次跟进时间：</label></td>
					<td class="width-35">
						<form:input path="lastTime" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>累计延时申请次数：</label></td>
					<td class="width-35">
						<form:input path="deayTimes" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>