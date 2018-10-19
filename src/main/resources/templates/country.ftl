<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Country Management</title>

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
            <h1>Country Management</h1>
        </div>
        <div class="row">
            <form action="/country/new" method="POST" class="col">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input name="name" id="name" type="text" class="form-control" >
                </div>

                <div class="form-group">
                    <label for="acronym">Acronym</label>
                    <input name="acronym" id="acronym" type="text" class="form-control" >
                </div>

                <div class="form-group">
                    <label for="phoneDigits">Phone Digits</label>
                    <input name="phoneDigits" id="phoneDigits" type="number" class="form-control" >
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
                        <th>Acronym</th>
                        <th>Phone Digits</th>
                    </tr>
                </thead>
                <tbody>
                <#list countryList as country>
                    <tr>
                        <td>${country.id}</td>
                        <td>${country.name}</td>
                        <td>${country.acronym}</td>
                        <td>${country.phoneDigits}</td>
                    </tr>
                </#list>    
                </tbody>
            </table>
        </div>
    </div>

</body>

</html>