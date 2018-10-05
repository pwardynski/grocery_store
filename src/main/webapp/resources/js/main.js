$(document).ready(function () {
		
        show_all_products();
        
        bind_add_new_product_or_update_existing();

});

function bind_delete_buttons() {
	
	$(".delete_button").click(function (event) {

    	var code = $(this).data('productCode');
    	
    	delete_product_by_code(code);

    });
}

function bind_add_new_product_or_update_existing() {
	
	$("#new-product-form").submit(function (event) {

        event.preventDefault();
        
        var is_in_table = false;
        	
        	var code = $('#product_code').val();
        	
        	$('td.code_cell').each(function() {
        	    var tmpCode = $(this).html();   
        	    if(tmpCode == code) {is_in_table = true;}
        	 });
        	
        
        if(is_in_table) {update_product();}
        else {add_new_product();}

        

    });
	
}

function show_all_products() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/grocery_store/products",
        cache: false,
        timeout: 600000,
        success: function (data) { 	

            var json = ""
                 data.forEach(function(entry){
                	json += "<tr>" + 
                	"<td class=\"code_cell\">" + entry.code + "</td>" +
                	"<td>" + entry.name + "</td>" +
                	"<td>" + entry.category + "</td>" +
                	"<td>" + entry.description + "</td>" +
                	"<td><button class=\"delete_button\" data-product-code=\""+ entry.code +"\">Delete</button></td>" +
                	+ "</tr>";
                });
            	
            $('#list_of_products').html(json);
            
            bind_delete_buttons();

            console.log("SUCCESS : ", data);

        },
        error: function (e) {

            var json =  e.responseText;
            $('#list_of_products').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}

function delete_product_by_code(code) {


    $.ajax({
        type: "DELETE",
        url: "/grocery_store/products/" + code,
        cache: false,
        timeout: 600000,
        success: function () {

        	show_all_products();

        },
        error: function (e) {

            var json = "<br><h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}

function add_new_product() {

    var product = {}
    product["code"] = $("#product_code").val();
    product["name"] = $("#product_name").val();
    product["category"] = $("#product_category").val();
    product["description"] = $("#product_description").val();

    $("#btn-add").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/grocery_store/products",
        data: JSON.stringify(product),
        cache: false,
        timeout: 600000,
        success: function () {

        	show_all_products();
        	
            $("#btn-add").prop("disabled", false);
        	
        	var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);
            
            console.log("SUCCESS : ", data);
        },
        error: function (jqXHR, textStatus, errorThrown ) {

            var json = "<h4>Ajax Error Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-add").prop("disabled", false);

        }
    });

}

function update_product() {

    var product = {}
    product["code"] = $("#product_code").val();
    product["name"] = $("#product_name").val();
    product["category"] = $("#product_category").val();
    product["description"] = $("#product_description").val();

    $("#btn-add").prop("disabled", true);

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/grocery_store/products/" + product["code"],
        data: JSON.stringify(product),
        cache: false,
        timeout: 600000,
        success: function () {

        	show_all_products();
        	
            $("#btn-add").prop("disabled", false);
        	
        	var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);
            
            console.log("SUCCESS : ", data);
        },
        error: function (jqXHR, textStatus, errorThrown ) {

            var json = "<h4>Ajax Error Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-add").prop("disabled", false);

        }
    });

}