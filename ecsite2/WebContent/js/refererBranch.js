(function() {
	const searchBtn = document.getElementById('SearchItemAction_0');
	const headerJspName = document.getElementById('header_jspName');
	const footerJspName = document.getElementById('jspName');
	const jspName = footerJspName.getAttribute('value');
	searchBtn.addEventListener('click', function() {
		headerJspName.setAttribute('value', jspName);
	});
}());