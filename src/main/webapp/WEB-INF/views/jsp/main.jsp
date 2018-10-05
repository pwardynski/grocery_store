<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Grocery Store - main page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body>

<div class="container" style="min-height: 500px">

    <div class="starter-template">
        <h1>Grocery Store</h1>

        <table>
        <thead>
        <tr>
   			<th>Code</th>
    		<th>Name</th>
    		<th>Type</th>
    		<th>Description</th>
    		<th>Option</th>
  		</tr>
  		</thead>
  		<tbody id="list_of_products"></tbody>
        </table><br><br>
        
        <div id="feedback"></div>
		
        <form class="form-horizontal" id="new-product-form">
            <div class="product-data">
            <h3>Fill in the form to add new product</h3>
            <table>
                   <tr><td > code: </td> <td><input type="number" class="product-code" id="product_code"/></td></tr>
                   <tr><td> name: </td> <td><input type="text" class="product-name" id="product_name"/></td></tr>
                   <tr><td> fruit/vegetable:</td> <td> <input type="text" class="product-category" id="product_category"/></td></tr>
                   <tr><td> short description: </td> <td><input type="text" class="product-description" id="product_description"/></td></tr>
            </table><br>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="btn-add"
                            class="btn btn-primary btn-lg">Add or Update
                    </button>
                </div>
            </div>
        </form>

    </div>

</div>


<script src="https://code.jquery.com/jquery-2.2.4.js"
		integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
		crossorigin="anonymous"></script>

<script type="text/javascript" src="resources/js/main.js"></script>


</body>
</html>