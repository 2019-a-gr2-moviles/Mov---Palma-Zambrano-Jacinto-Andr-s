/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombreAtributo: {
      type:'string'
    },
    nombre: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 60,
    },
    cedula: {
      type: 'string',
      required: true,
      unique: true,
      minLength: 10,
      maxLength: 25,
    },
    username: {
      type: 'string',
      required: true,
      unique: true
    },
    fechaNacimiento: {
      type: 'string'
    },
    sueldo: {
      type: 'number',
      min: 100.00,
      max: 5000,
      defaultsTo: 100.00
    },
    estaCasado: {
      type: 'boolean',
      defaultsTo: false
    },
    latitudCasa: {
      type: 'string'
    },
    longitudCasa: {
      type: 'string'
    },
    tipoUsuario: {
      type: 'string',
      enum: ['normal', 'pendiente', 'premium']
    },
    correo: {
      type: 'string',
      isEmail: true
    },
    // CONFIGURACION DEL PAPA
    serviciosDeUsuario: { // Nombre atributo de la relacion
      collection: 'servicio', // Nombre del modelo a relacionar
      via: 'fkUsuario'  // Nombre atributo FK del otro modelo
    }
  },
  // 

};

// http://localhost:1337/usuario

// ESTANDAR RESTFUL

// CREAR
// http://localhost:1337/usuario
// METODO HTTP: POST
// Body Params: usuario

// ACTUALIZAR
// http://localhost:1337/usuario/:id
// Ej: http://localhost:1337/usuario/2
// METODO HTTP: PUT
// Body Params: usuario

// BORRAR
// http://localhost:1337/usuario/:id
// Ej: http://localhost:1337/usuario/2
// METODO HTTP: DELETE

// BUSCAR POR ID
// http://localhost:1337/usuario/:id
// Ej: http://localhost:1337/usuario/2
// METODO HTTP: GET

// OBTENER TODOS ( ENVIAR PARAMETROS BUSQUEDA )
// http://localhost:1337/usuario
// METODO HTTP: GET


// EJEMPLOS

// 1) Buscar al usuario con nombre Adrian
// http://localhost:1337/usuario?nombre=Adrian

// 2) Buscar al usuario con nombre Adrian y cedula 1718137159
// http://localhost:1337/usuario?nombre=Adrian&cedula=1718137159

// 3) Traer los primeros 5
// http://localhost:1337/usuario?limit=5


// 4) Traer los primeros 5 despues de los primeros 10
// http://localhost:1337/usuario?limit=5&skip=10


// 5) Traer los registros ordenados por nombre
// http://localhost:1337/usuario?sort=nombre
// http://localhost:1337/usuario?sort=nombre DESC
// http://localhost:1337/usuario?sort=nombre ASC

// 6) Traer los registros ordenados por nombre
// http://localhost:1337/usuario?sort=nombre
// http://localhost:1337/usuario?sort=nombre DESC
// http://localhost:1337/usuario?sort=nombre ASC


// 6) Traer los registros que contengan en el nombr la letra a
// http://localhost:1337/usuario?where={"nombre":{"contains":"a"}}
// http://localhost:1337/usuario?where={"sueldo":{"<=":3000}}
// http://localhost:1337/usuario?where={"fechaNacimiento":{"<=":"2018-01-01"}}
// http://localhost:1337/usuario?where={"correo":{"endsWith":"@gmail.com"}}
