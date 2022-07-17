function changeImage(response) {
    debugger;
    switch (response) {
        case '12':
            domNode.src = "/images/img1.jpg"
            break;
        case '2':
        case '3':
            domNode.src = "/images/img1.jpg"
            break;
        default:
    }
}

function changeImgOfTank(p)
{

    let tankId = p.value;
    fetch('/tank/getCategoryId?tankId='+tankId)
        .then(changeImage);


}