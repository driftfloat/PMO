$(function(){
	
	loadRole();
	loadSkill();
	loadSource();
	
});

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadSource(){
	var url = path+'/json/source.json'
	$.getJSON(url,  function(data) {
		$.each(data, function(i, item) {
			$("#source").append("<option>"+item.name+"</option>");
		})
	});
}


function loadRole(){
	var url = path+'/json/msaRole.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}
