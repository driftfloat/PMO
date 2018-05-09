$(function () {
	//加载线下运营报表
    loadOfflineReport();
});


//线下运营报表
function loadOfflineReport(){
	
    var queryUrl = path+'/service/offlineOper/querySummary';
    var table = $('#OfflineReport').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        fixedColumns: true,
        fixedNumber: 3,
        singleSelect : true,                // 单选checkbox 
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
        showColumns: false,                  //是否显示所有的列（选择显示的列）
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
        columns: [
        {
            field: 'departmentName',
            title: 'DepartMent',
            sortable: true,
            formatter : function(value,row, index){
                //if(index>=1){
                	//return "";
                //}else{
                	 var div = "<div style='width:200px;'>"+value+"</div>";
               	     return div;
               // }
            }
        },
        {
            field: 'type',
            title: 'Type',
            sortable: true,
            formatter : function(value, row, index){
            	  var div = "<div style='width:200px;'>"+value+"</div>";
            	  return div;
            }
        },
        {
            field: 'remark',
            title: 'Remark',
            sortable: true,
            formatter : function(value, row, index){
          	  var div = "<div style='width:255px;'>"+value+"</div>";
          	  return div;
            }
        },
        {
            field: 'month',
            title: '1月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month1;
            }
        },
        {
            field: 'month',
            title: '2月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month2;
            }
        },{
            field: 'month',
            title: '3月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month3;
            }
        },{
            field: 'month',
            title: '4月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month4;
            }
        },{
            field: 'month',
            title: '5月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month5;
            }
        },{
            field: 'month',
            title: '6月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month6;
            }
        },{
            field: 'month',
            title: '7月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month7;
            }
        },{
            field: 'month',
            title: '8月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month8;
            }
        },{
            field: 'month',
            title: '9月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month9;
            }
        },{
            field: 'month',
            title: '10月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month10;
            }
        },{
            field: 'month',
            title: '11月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month11;
            }
        },{
            field: 'month',
            title: '12月',
            sortable: true,
            formatter : function(value,row, index){
                return value.month12;
            }
        },{
            field: 'yearTotal',
            title: 'Total',
            sortable: true
        }
        ],
        onLoadSuccess: function () {
        	 
        },
        onLoadError: function () {
        	
        },
        onDblClickRow: function (row, $element) {
           
        }
        
    });
}

/**
 * 合并单元格
 * @param data  原始数据（在服务端完成排序）
 * @param fieldName 合并属性名称
 * @param colspan   合并列
 * @param target    目标表格对象
 */
function mergeCells(data,fieldName,colspan,target){
    //声明一个map计算相同属性值在data对象出现的次数和
    var sortMap = {};
    for(var i = 0 ; i < data.length ; i++){
        for(var prop in data[i]){
            if(prop == fieldName){
                var key = data[i][prop]
                if(sortMap.hasOwnProperty(key)){
                    sortMap[key] = sortMap[key] * 1 + 1;
                } else {
                    sortMap[key] = 1;
                }
                break;
            } 
        }
    }
    for(var prop in sortMap){
        console.log(prop,sortMap[prop])
    }
    var index = 0;
    for(var prop in sortMap){
        var count = sortMap[prop] * 1;
        $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});   
        index += count;
    }
}

//导出
function exportData(){
	uri = path+'/service/offlineOper/export',
	window.location.href=uri;
}

