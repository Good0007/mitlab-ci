
function addActiveNav(navName){
	var navs = $("ul.navbar-nav li a");
	for(var i=0 ;i<navs.size(); i++){
		var nav = navs.eq(i);
		if(nav.html().trim()==navName){
			nav.parent().addClass("active");
			return;
		}
	}
}