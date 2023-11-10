async function enviarMensajeDesdeDOM() {
    //Creo un mensaje
    let mensaje = {
    }
     //Obtengo los datos del DOM
    mensaje.cantidadDigitoImpar = document.getElementById('cantidadImpar').value    
    mensaje.cantidadDigitoPar = document.getElementById('cantidadPar').value 
    mensaje.textoMensaje = document.getElementById('texto').value 
   
    //Realizo la request al servidor
    const request= await fetch('enviarMensaje', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(mensaje)
    });
        const mensajeRecibido = await request.text();
        document.getElementById('resultadoEncriptado').textContent = mensajeRecibido;
    
}





