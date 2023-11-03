
const mobile_btn = document.querySelector('.mobile_btn')
const primary_nav = document.querySelector('.primary_nav')

mobile_btn.addEventListener('click', function(){
    primary_nav.classList.toggle('active')
})


const btn_modal1 = document.querySelector('.primary_btn')
const modal1 = document.querySelector('.modal_1')
const email = document.querySelector('#email')
const tel = document.querySelector('#text')

btn_modal1.onclick = function(){
    modal1.showModal()
}

const buttonSendt = document.querySelector('.ButtonSend')

buttonSend.onclick = function(){
    modal1.close()
    window.alert('Aguarde Contato')
    email.value = "";
    tel.value = "";
}