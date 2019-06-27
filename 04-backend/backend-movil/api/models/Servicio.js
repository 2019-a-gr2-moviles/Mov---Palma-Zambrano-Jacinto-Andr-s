/**
 * Servicio.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre: {
      type:'string'
    },
    //  CONFIGURACION DEL HIJO
    fkUsuario: {  // Nombre del FK para la relacion
      model: 'usuario', // Nombre del modelo a relacionar (papa)
      // required: true // OPCIONAL -> Siempre ingresar el FK
    }
  },

};

