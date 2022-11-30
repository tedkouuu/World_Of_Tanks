function changeImgOfTank(selectNode) {
    let tankId = selectNode.value;

    let callback = (response) => {
        let id = JSON.parse(response).category.id;
        selectNode.parentNode.parentNode.getElementsByTagName("img")[0].src = "/images/tank-" + id + ".jpg";
    };

    fetch('/tank/getCategoryId/' + tankId)
        .then(response => response.text())
        .then(callback);
}
