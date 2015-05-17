$ ->
	$.get "/products", (products) -> 
		$.each products, (addProduct , product) ->
			$('#persons').append $("<li>").text product.name 