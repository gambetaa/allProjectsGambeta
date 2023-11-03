const c_painel = document.querySelector('.c_painel');
const oficinas_painel = document.querySelector('.oficinas_painel');
const painel_sinistro = document.querySelector('.painel_sinistro');
const frota_painel = document.querySelector('.frota_painel');

const btn_action1 = document.querySelector('.btn_action1');
const btn_action2 = document.querySelector('.btn_action2');
const btn_action3 = document.querySelector('.btn_action3');
const btn_action4 = document.querySelector('.btn_action4');
const btn_action5 = document.querySelector('.btn_action5');



btn_action1.onclick = function (){
    c_painel.showModal()
}
btn_action2.onclick = function (){
    oficinas_painel.showModal()
}
btn_action4.onclick = function (){
    painel_sinistro.showModal()
}
btn_action5.onclick = function (){
    frota_painel.showModal()
}/* 
btn_action4.onclick = function (){
    c_painel.showModal()
} */

const buttonSend = document.querySelector('.ButtonSend')
const buttonSend2 = document.querySelector('.ButtonSend2')
const buttonSend3 = document.querySelector('.ButtonSend3')
const buttonSend4 = document.querySelector('.ButtonSend4')

buttonSend.onclick = function(){
    c_painel.close()
    window.alert('Apólice Cadastrada')
}
buttonSend2.onclick = function(){
    oficinas_painel.close()
}
buttonSend3.onclick = function(){
    painel_sinistro.close()
    window.alert('Entraremos em contato em breve')
}
buttonSend4.onclick = function(){
    frota_painel.close()
    window.alert('Apólice enviada!')
}