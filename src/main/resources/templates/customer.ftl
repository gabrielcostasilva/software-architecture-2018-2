<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Customer Management</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Customer Management</h1>
        </div>
        <div class="row">
            <form action="/customer/new" method="POST" class="col">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" class="form-control">
                </div>

                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" name="phone" id="phone" class="form-control">
                </div>

                <div class="form-group">
                    <label for="age">Age</label>
                    <input type="number" name="age" id="age" class="form-control">
                </div>

                <div class="form-group">
                    <label for="country">Country</label>
                    <select name="country" id="country" class="form-control">
                    <#list countryList as country>
                        <option>${country.name}</option>
                    </#list>
                    </select>
                </div>

                <input type="submit" value="Create" class="btn btn-primary">
            </form>

        </div>
        <div class="row table-responsive">
            <table class="col table table-striped table-hover">
                <thead class="thead-light">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Age</th>
                        <th>Credit Limit</th>
                        <th>Country</th>
                    </tr>
                </thead>
                <tbody>
                    <#list customerList as customer>
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.name}</td>
                            <td>${customer.phone}</td>
                            <td>${customer.age}</td>
                            <td>${customer.creditLimit}</td>
                            <td>${customer.country.name}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>


    </div>



</body>

</html>