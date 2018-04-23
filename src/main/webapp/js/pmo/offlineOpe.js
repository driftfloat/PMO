$(function () {
	//加载线下运营列表
    loadOfflineOperList();
});


//线下运营列表
function loadOfflineOperList(){
	
    var queryUrl = path+'/service/offlineOper/query';
	//var queryUrl = path+'/json/test.json';
    var table = $('#OfflineOperList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [{
            checkbox: true,  
            visible: true                  //是否显示复选框  
        }, 
        {
            field: 'year',
            title: 'Year',
            sortable: true
        },
        {
            field: 'month',
            title: 'Month',
            sortable: true
        },
        {
            field: 'staffName',
            title: 'StaffName',
            sortable: true
        }, 
        {
            field: 'eHr',
            title: 'EHr',
            sortable: true
        },
        {
            field: 'chsoftiAwHours',
            title: 'AwHours',
            editable: {
                type: 'text',
                title: '中软实际工时',
                validate: function (v) {
                    if (!v) return '中软实际工时不能为空';

                }
            },
            sortable: true
        }, 
        {
            field: 'chsoftiIwHours',
            title: 'IwHours',
            editable: {
                type: 'text',
                title: '中软无效工时',
                validate: function (v) {
                    if (!v) return '中软无效工时不能为空';

                }
            },
            sortable: true
        },
        {
            field: 'chsoftiOtHours',
            title: 'OtHours',
            editable: {
                type: 'text',
                title: '中软加班费工时',
                validate: function (v) {
                    if (!v) return '中软加班费工时不能为空';

                }
            },
            sortable: true
        },
        {
            field: 'chsoftiToHours',
            title: 'ToHours',
            editable: {
                type: 'text',
                title: '中软调休工时',
                validate: function (v) {
                    if (!v) return '中软调休工时不能为空';

                }
            },
            sortable: true
        },
        {
            field: 'chsoftiApwHours',
            title: 'ApwHours',
            editable: {
                type: 'text',
                title: '中软调整上月工时',
                validate: function (v) {
                    if (!v) return '中软调整上月工时不能为空';

                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfTravel',
            title: 'InfTravel',
            editable: {
                type: 'text',
                title: '差旅收入',
                validate: function (v) {
                    if (!v) return '差旅收入不能为空';

                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfEquipment',
            title: 'InfEquipment',
            editable: {
                type: 'text',
                title: '付费设备收入',
                validate: function (v) {
                    if (!v) return '付费设备收入不能为空';

                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfSub',
            title: 'InfSub',
            editable: {
                type: 'text',
                title: '分包收入',
                validate: function (v) {
                    if (!v) return '分包收入不能为空';

                }
            },
            sortable: true
        },
        {
            field:'id',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle'
        }],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
            //showTips("数据加载失败！");
        },
        onDblClickRow: function (row, $element) {
            //var id = row.ID;
           // EditViewById(id, 'view');
        },
    });
}

function search(){
	//获取查询条件
	var staffName = $("#staffName").val();
	var ehr = $("#ehr").val();
	var queryParams = { 
		query: {  
		   name:staffName
        }
    }  
	//刷新表格  
    $('#OfflineOperList').bootstrapTable('refresh',queryParams);  
}