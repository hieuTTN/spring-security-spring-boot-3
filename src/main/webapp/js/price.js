    document.addEventListener("DOMContentLoaded", function() {
    const elements = document.querySelectorAll(".giaspformat");
    elements.forEach(element => {
    const money = parseFloat(element.textContent);
    if (!isNaN(money)) {
    element.textContent = formatmoney(money);
}
});
});
    function formatmoney(money) {
    const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});
    return VND.format(money);
}