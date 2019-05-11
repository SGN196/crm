/**
 * 跟进记录信息
 */
$(function () {
    //初始化表格
    $("#followUpTable").bootstrapTable({
        //请求方法
        method: 'post',
        //类型json
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        //显示检索按钮
        showSearch: false,
        //显示刷新按钮
        showRefresh: true,
        //显示切换手机试图按钮
        showToggle: true,
        //显示 内容列下拉框
        showColumns: true,
        //显示到处按钮
        showExport: true,
        //显示切换分页按钮
        showPaginationSwitch: true,
        //最低显示2行
        minimumCountColumns: 2,
        //是否显示行间隔色
        striped: true,
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        //是否显示分页（*）
        pagination: true,
        //排序方式
        sortOrder: "asc",
        //初始化加载第一页，默认第一页
        pageNumber:1,
        //每页的记录行数（*）
        pageSize: 10,
        //可供选择的每页的行数（*）
        pageList: [10, 25, 50, 100],
        //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
        url: "${ctx}/opportunity/opportunity/data",
        //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
        //queryParamsType:'',
        ////查询参数,每次调用是会带上这个参数，可自定义
        queryParams : function(params) {
            var searchParam = $("#searchForm").serializeJSON();
            searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
            searchParam.pageSize = params.limit === undefined? -1 : params.limit;
            searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
            return searchParam;
        },
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination: "server",
        contextMenuTrigger:"right",//pc端 按右键弹出菜单
        contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
        contextMenu: '#context-menu',
        onContextMenuItem: function(row, $el){
            if($el.data("item") == "edit"){
                edit(row.id);
            }else if($el.data("item") == "view"){
                view(row.id);
            } else if($el.data("item") == "delete"){
                jp.confirm('确认要删除该线索商机记录吗？', function(){
                    jp.loading();
                    jp.get("${ctx}/opportunity/opportunity/delete?id="+row.id, function(data){
                        if(data.success){
                            $('#opportunityTable').bootstrapTable('refresh');
                            jp.success(data.msg);
                        }else{
                            jp.error(data.msg);
                        }
                    })

                });

            }
        },

        onClickRow: function(row, $el){
        },
        onShowSearch: function () {
            $("#search-collapse").slideToggle();
        },
        columns: [{
                checkbox: true
            }
            ,{
                field: 'oppNo',
                title: '序号',
                sortable: true,
                sortName: 'oppNo'
            }
            ,{
                field: 'oppName',
                title: '商机名称',
                sortable: true,
                sortName: 'oppName'

            }
            ,{
                field: 'status',
                title: '商机状态',
                sortable: true,
                sortName: 'status'

            }
            ,{
                field: 'custId',
                title: '联系人',
                sortable: true,
                sortName: 'custId'

            }
            ,{
                field: 'lastTime',
                title: '跟进时间',
                sortable: true,
                sortName: 'lastTime'

            }
            ,{
                field: 'demand',
                title: '需求描述',
                sortable: true,
                sortName: 'demand'

            }
            ,{
                field: 'empId',
                title: '跟进人员编号',
                sortable: true,
                sortName: 'empId'

            }
            ,{
                field: 'Time',
                title: '记录时间',
                sortable: true,
                sortName: 'Time'

            }
        ]

    });

    //查询
    $("#search").click("click", function() {// 绑定查询按扭
        $('#followUpTable').bootstrapTable('refresh');
    });

    //重置
    $("#reset").click("click", function() {// 绑定查询按扭
        $("#searchForm  input").val("");
        $("#searchForm  select").val("");
        $("#searchForm  .select-item").html("");
        $('#followUpTable').bootstrapTable('refresh');
    });

    //适应移动端
    if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){
        $('#followUpTable').bootstrapTable("toggleView");
    }
    //列表上的工具栏按钮是否禁止
    $('#followUpTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        $('#remove').prop('disabled', ! $('#followUpTable').bootstrapTable('getSelections').length);
        $('#view,#edit').prop('disabled', $('#followUpTable').bootstrapTable('getSelections').length!=1);
    });
});
//获取跟进记录ID
function getIdSelections() {
    return $.map($("#followUpTable").bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

//新增跟进记录
function follow_add() {
    jp.openSaveDialog('新增商机跟进', "${ctx}/opportunity/opportunity/followForm",'800px', '500px');
}

//修改跟进记录
function follow_edit(id) {
    if(id == undefined){
        id = getIdSelections();
    }
    jp.openSaveDialog('编辑商机跟进', "${ctx}/opportunity/opportunity/followForm?id=" + id,'800px', '500px');
}

//删除跟进记录
function follow_del(){
    jp.confirm('确认要删除该商机跟进吗？', function(){
        jp.loading();
        jp.get("${ctx}/opportunity/opportunity/deleteAll?ids=" + getIdSelections(), function(data){
            if(data.success){
                $('#followUpTable').bootstrapTable('refresh');
                jp.success(data.msg);
            }else{
                jp.error(data.msg);
            }
        })
    })
}