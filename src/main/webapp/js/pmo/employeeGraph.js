var csBU01 = "";
var csSubDept01 = "";
function dateType(){
	$('.form_datetime').datetimepicker({
		endDate:new Date(),
		weekStart: 1,
		minView:'month',
		todayBtn:  2,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	});
}

function loadCSBu(csBU){
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
	       $('#csBu').val(csBU);
	});
}

function loadCSSubDept(csSubDept){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#csSubDept").empty();
			$("#csSubDept").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option value = "+list[i].csSubDeptId+">"+list[i].csSubDeptName+"</option>");
			}
			$('#csSubDept').val(csSubDept);
		}
	})
}

$(function(){
	
	dateType();
	loadEmployeeGraph();
})

$('#searchBtn').bind("click", function(){
	
	loadEmployeeGraph();
});

function showTooltip(x, y, contents) {
    $('<div id="tooltip">' + contents + '</div>').css({
        position: 'absolute',
        display: 'none',
        top: y + 5,
        left: x + 5,
        border: '1px solid #fdd',
        padding: '2px',
        'background-color': '#dfeffc',
        opacity: 0.80
    }).appendTo("body").fadeIn(200);
}

function loadEmployeeGraph(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var csSubDept = $("#csSubDept").val();
	var csBU = $("#csBu").val();
	var csBUName = "";
	if(null != csBU && csBU !=""){
		csBUName = $("#csBu").find("option:selected").text();
	}
	if("" == startDate || "" == endDate){
		startDate = new Date().format("yyyy-01-01");
		endDate = new Date().format("yyyy-MM-dd");
	}
	$("#startDate").val(startDate);
	$("#endDate").val(endDate);
	loadCSBu(csBU);
	loadCSSubDept(csSubDept);
	
	$.ajax({
		url:path+"/service/employee/employeeStatisticalGraph",
		dataType:"json",
		async:true,
		data:{"startDate":startDate, "endDate":endDate, "csSubDept":csSubDept, "csBUName":csBUName},
		cache:false,
		type:"post",
		success:function(result){
			if ($("#statisticalGraph").length) {
				var releaseData = [], levelData = [], terminatedData = [], intoDUData = [], outDUData = [];
			    var releaseMap = result.release;
			    var levelMap = result.level;
			    var terminatedMap = result.terminated;
			    var intoDUMap = result.intoDUMap;
			    var outDUMap = result.outDUMap;
			    var ymMap = result.ymMap;
			    var xSet = [];
			    var xDataMap = result.ymDataMap;
			    for (var setKey in xDataMap){
			    	var xSet = xDataMap[setKey];
			    }
			    for(var ymKey in ymMap){
			    	var startYm = ymKey;
			    	var endYm = ymMap[ymKey];
			    }
			    var arr01 = [];
				for(var i=1; i<=xSet.length; i++){
					arr01.push([i,xSet[i-1]]);
				}
			    releaseData = dataFormat(releaseMap,releaseData);
			    levelData = dataFormat(levelMap,levelData);
			    terminatedData = dataFormat(terminatedMap,terminatedData);
			    intoDUData = dataFormat(intoDUMap,intoDUData);
			    outDUData = dataFormat(outDUMap,outDUData);

			    var plot = $.plot($("#statisticalGraph"),
			        [
			        	{ data: levelData, label: "Level Change" },
			            { data: releaseData, label: "Release"},
			            { data: terminatedData, label: "Terminated"},
			            { data: intoDUData, label: "IntoDU"},
			            { data: outDUData, label: "OutDU"}
			        ], {
			            series: {
			                lines: { show: true  },
			                points: { show: true }
			            },
			            grid: { hoverable: true, clickable: true, backgroundColor: { colors: ["#fff", "#eee"] } },
			            xaxis: { ticks: arr01 },
			            yaxis: { min: 0, max: 100 },
			            colors: ["#1E90FF", "#FF8000", "#B22222", "#FFFFCD", "#808069"],
			            legend: {position: "nw"}
			        });

			    var previousPoint = null;
			    $("#statisticalGraph").bind("plothover", function (event, pos, item) {
			        $("#x").text(pos.x.toFixed(2));
			        $("#y").text(pos.y.toFixed(2));

			        if (item) {
			            if (previousPoint != item.dataIndex) {
			                previousPoint = item.dataIndex;

			                $("#tooltip").remove();
			                var x = item.datapoint[0],
			                    y = item.datapoint[1];
			                var xDate = item.series.xaxis.ticks[x-1].label;

			                showTooltip(item.pageX, item.pageY,
			                    item.series.label + " of " + xDate + " is " + y);
			            }
			        }
			        else {
			            $("#tooltip").remove();
			            previousPoint = null;
			        }
			    });


			    $("#statisticalGraph").bind("plotclick", function (event, pos, item) {
			        if (item) {
			        	var x = item.datapoint[0];
			        	var xDate = item.series.xaxis.ticks[x-1].label;
			            $("#clickdata").text("You clicked point " + xDate + " in " + item.series.label + ".");
			        }
			    });
			}
		}
	})
	
	
}

function dataFormat(map, data){
	for(var key in map){
		data.push([parseInt(key), map[key]]);
	}
	return data;
}

// Date Format
Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,
       "d+" : this.getDate(),
       "h+" : this.getHours(),
       "m+" : this.getMinutes(),
       "s+" : this.getSeconds(),
       "q+" : Math.floor((this.getMonth()+3)/3),
       "S"  : this.getMilliseconds()
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}