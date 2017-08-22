$(function(){
	jQuery.i18n.properties({
		name:'strings_en', 
		path:path+'/i18n/', 
		mode:'map',
		callback: function() {
			$('.username').html($.i18n.prop('string_username'));
		}
	});
	
});


