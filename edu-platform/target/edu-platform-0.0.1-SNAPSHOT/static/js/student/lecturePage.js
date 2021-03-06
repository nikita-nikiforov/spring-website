// Get courseId from url path
var url = window.location.href;
var courseId = parseInt(url.match(/course-\d+/)[0].match(/\d+/));

// Get lectureId from url path
var lectureId = parseInt(url.match(/\/\d+/)[0].match(/\d+/));

console.log(courseId, " ", lectureId);
$(document).ready(function () {
    console.log("hi2!");
    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        console.log("state changed");
        console.log("this.readyState: " + this.readyState);
        console.log("this.status: " + this.status);
        if (this.readyState == 4 && this.status == 200) {
            var blob = req.response;
            $('#pdf-container').attr('src', window.URL.createObjectURL(blob));
            console.log("hi!");
        }
    };
    req.open("GET", "/displayPDF?id=" + lectureId, true);
    req.responseType = "blob";
    req.send();
});
