from flask import Blueprint, abort, request, render_template, redirect
import json
import requests
from flask import flash
router = Blueprint('router', __name__)

router = Blueprint('router', __name__)

@router.route('/')
def index():
    return render_template('template.html')

@router.route('/generadores')
def generadores():
    r = requests.get('http://localhost:8080/api/generador/list')
    data =  r.json()
    return render_template('generador/listas.html', lista = data["data"])

@router.route('/generadores/guardar', methods=['POST'])
def guardar_generador():
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        'modelo': form['mod'],
        'costo': form['cos'],
        'consumoPorHora': form['cph'],
        'podruccionEnergia': form['pce'],
        'uso': form['uso'],
        'numeroSerie': form['ns']
    }
    r = requests.post('http://localhost:8080/api/generador/create', headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash('Generador guardado correctamente')
        return redirect('/generadores')
    else:
        flash(str(dat["message"]),'Error al guardar el generador')
        return redirect('/generadores')
    
@router.route('/generadores/formulario')
def formulario_generador():
    return render_template('generador/guardar.html')

@router.route('/generadores/editar/<id>')
def cambiargenerador(id):
    r = requests.get('http://localhost:8080/api/generador/list/'+id)
    data = r.json()
    if(r.status_code == 200):
        return render_template('generador/editar.html', lista = data["data"])
    else:
        flash(data["data"],'Error al cargar el generador')
        return redirect('/generadores') 

@router.route('/generadores/editar', methods=['POST'])
def update_generador():
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        'id': form['id'],
        'modelo': form['mod'],
        'costo': form['cos'],
        'consumoPorHora': form['cph'],
        'podruccionEnergia': form['pce'],
        'uso': form['uso'],
        'numeroSerie': form['ns']
    }
    r = requests.post('http://localhost:8080/api/generador/update', headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash('Generador guardado correctamente')
        return redirect('/generadores')
    else:
        flash(str(dat["message"]),'Error al guardar el generador')
        return redirect('/generadores')



@router.route('/familias/<numeroSerie>')   
def familias(numeroSerie):
    r = requests.get('http://localhost:8080/api/familia/lists/'+numeroSerie)
    data =  r.json()
    r = requests.get('http://localhost:8080/api/generador/list')
    dataG = r.json()

    generador_seleccionado = next((generador for generador in dataG["data"] if generador["numeroSerie"] == numeroSerie), None)
    return render_template('familia/lista.html', lista = data["data"], listag = dataG["data"], generador = generador_seleccionado)

@router.route("/familias/listas/<numeroSerie>")
def lista_familias(numeroSerie):
    r = requests.get('http://localhost:8080/api/familia/lists/'+numeroSerie)
    data = r.json()
    r = requests.get('http://localhost:8080/api/generador/list')
    dataG = r.json()

    generador_seleccionado = next((generador for generador in dataG["data"] if generador["numeroSerie"] == numeroSerie), None)
    return render_template('familia/guardar.html', lista = data["data"], listag = dataG["data"], generador = generador_seleccionado)

@router.route('/familias/render/<numeroSerie>')
def renderizar(numeroSerie):
    r = requests.get('http://localhost:8080/api/familia/lists/'+numeroSerie)
    data = r.json()

    r = requests.get('http://localhost:8080/api/generador/list')
    dataG = r.json()

    generador_seleccionado = next((generador for generador in dataG["data"] if generador["numeroSerie"] == numeroSerie), None)
    if r.status_code == 200:
        return render_template('template2.html', lista = data["data"], listag = dataG["data"] , generador = generador_seleccionado)
    else:
        flash('Error al cargar las familias')
        return redirect('/generadores')
    

@router.route('/familias/guardar/<numeroSerie>', methods=['POST'])
def guardar_familia(numeroSerie):
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        'nombre': form['nom'],
        'numeroMiembros': form['nm'],
    }
    r = requests.post('http://localhost:8080/api/familia/add/'+numeroSerie, headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash('Familia guardada correctamente')
        return redirect('/familias/'+numeroSerie)
    else:
        flash(str(dat["message"]),'Error al guardar la familia')
        return redirect('/familias/'+numeroSerie)
