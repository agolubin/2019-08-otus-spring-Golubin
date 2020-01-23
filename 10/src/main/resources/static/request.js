    $(function () {
        load();
    });

function load() {
        $.get('/books').done(function (books) {
            $("tbody").empty();
            books.forEach(function (book) {
                $('tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.author.name} ${book.author.surName}</td>
                        <td>${book.genre.name}</td>
                        <td>${book.name}</td>
                        <td><button type='button' class='btn btn-secondary' onclick='findBookByID(\"" + ${book.id} + "\")'>Изменить</button></td>
                        <td><button type='button' class='btn btn-secondary' onclick='deleteBook(\"" + ${book.id} + "\")'>Удалить</button></td>
                    </tr>
                `)
            });
        })
}

function deleteBook(id) {
    if (confirm('Удалить книгу?')) {
        $.ajax({
            url: "/books/" + id,
            type: "DELETE"
        }).done(function () {
            load();
        });
    }
}

function loadAndDisplayFormForNewBookCreation() {
    $.ajax({
        url: "add_book.html",
        }).done(function (response) {
            $("#content").html(response)
    });
}

function addBook() {
    var book = {
            name: $("#bookName").val(),
            genre: {
               name: $("#GenreName-input").val()
             },
            author: {
               name: $("#AuthorName-input").val(),
               surName: $("#AuthorSurName-input").val()
             },
        }
    $.ajax({
        url: "/books/",
        type: "POST",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(book)
        }).done(function (data) {
            load();
    });
}

function findBookByID(id) {
    $.ajax({
        url: "update_Book.html",
        success: function (response) {
                $("#content").html(response)
        }
    }).done(function () {
        $.ajax({
        url: "/books/" + id,
        type: 'GET',
        success: function(book) {
                    $("#bookID").val(book.id);
                    $("#GenreName-input").val(book.genre.name);
                    $("#AuthorName-input").val(book.author.name);
                    $("#AuthorSurName-input").val(book.author.surName);
                    $("#bookName").val(book.name);
                 }
        });
    });
}

function updateBook() {
    var book = {
            id: $("#bookID").val(),
            name: $("#bookName").val(),
            genre: {
               name: $("#GenreName-input").val()
             },
            author: {
               name: $("#AuthorName-input").val(),
               surName: $("#AuthorSurName-input").val()
             },
        }
    $.ajax({
        url: "/books/" + book.id,
        type: "PUT",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(book),
        }).done(function (data) {
            load();
    });
}