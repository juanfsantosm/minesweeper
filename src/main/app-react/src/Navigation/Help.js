import { Storage } from 'aws-amplify';

/**
 * Función para abrir el documento de ayuda.
 */
export default async (location: String) => {
    var path = 'manuales' + location + '.pdf';
    console.log('path bucket', path);
    var resultado;
    await Storage.get(path, {
        level: 'public',
        download: false
    })
    .then(result => {
        resultado = result;
    })
    .catch(err => {
        console.log('error al tratar de obtener bucket', err);
    });
    return  resultado;
}
