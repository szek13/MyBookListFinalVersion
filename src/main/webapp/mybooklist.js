/**
 * Created by Csongor on 4/5/2017.
 */

function listBooks(books) {
    var list = document.getElementById('listOfBooks').getElementsByTagName('ul')[0];
    var listHtml = '';

    for ( var i = 0; i < books.length; i++) {
        var book = books[i];
        var booksHtml = '<li> + book.name + </li>'
        listHtml += booksHtml;
    }
    list.innerHTML = listHtml;
}

function loadBooks() {
    $.ajax({
        url:'items?action=list'
    }).done(function (response) {
        listBooks(response.books)
    });

}

function addBook(){
    var bookTitle = document.getElementById('bookid').value;
    $.ajax({
        url: 'items?action=add&value='+bookTitle
    }).done(function(response){
        location.href="mybooklist.html";
    });
}

function isLogin() {
    $.ajax({
        url: 'items?action=seeLogin'
    }).done(function (response) {
        listLogin(response.keyError);
    });
}

function listLogin(keyError) {
    var elem = document.getElementById('login');

    elem.innerHTML = keyError+'<a href="login.html">Login</a>';
}