# Mira Code Challenge - Backend
Api constructed using Springboot and H2

TODO:
* Documentação clara e especifica
* Conclusão cobertura de testes

GET /pessoas

GET /pessoas?nome={nome}&sobrenome={sobrenome}&cpf={cpf}

GET /pessoas/{id}

PUT /pessoas/{id}
'''
{
"nome": "Bruno",
"sobrenome": "Delgado",
"cpf": "42840053837",
"dataNascimento": "07/12/1994",
"enderecos":[
{
"endereco": "Residencial",
"descricao": "Rua do Parque, 256"
}
],
"telefones":[
{
"id": "",
"numero": "Celular",
"descricao": "11964426990"
},
{
"numero": "Comercial",
"descricao": "1125003642"
}
],
"emails":[
{
"endereco": "Pessoal",
"descricao": "bruno.sdelgado@outlook.com"
}
],
"ativo": ""
}
'''

POST /pessoas
'''
{
"nome": "Bruno",
"sobrenome": "Delgado",
"cpf": "42840053837",
"dataNascimento": "07/12/1994",
"enderecos":[
{
"endereco": "Residencial",
"descricao": "Rua do Parque, 256"
}
],
"telefones":[
{
"id": "",
"numero": "Celular",
"descricao": "11964426990"
},
{
"numero": "Comercial",
"descricao": "1125003642"
}
],
"emails":[
{
"endereco": "Pessoal",
"descricao": "bruno.sdelgado@outlook.com"
}
],
}
'''

POST /bulk
'''
{
  "operations": [
    {"method": "GET", "url": "/pessoas/1"},
    {"method": "POST", "url": "/pessoas", "params":{"nome": "Marcos","sobrenome": "Delgado","cpf": "11577503899","dataNascimento": "07/12/1994","enderecos":[{"endereco": "Residencial","descricao": "Rua do Parque, 256"}],"telefones":[{"id": "","numero": "Celular","descricao": "11964426990"},{"numero": "Comercial","descricao": "1125003642"}],"emails":[{"endereco": "Pessoal","descricao": "bruno.sdelgado@outlook.com"}],"ativo": ""}},
    {"method": "PUT", "url": "/pessoas/2","params":{"id": 2,"nome": "Kaique","sobrenome": "Leao","cpf": "09842511659","dataNascimento": "07/12/1994","enderecos":[{"endereco": "Residencial","descricao": "Rua do Parque, 256"}],"telefones":[{"numero": "Celular","descricao": "11964426990"},{"numero": "Comercial","descricao": "1125003642"}],"emails":[{"endereco": "Pessoal","descricao": "bruno.sdelgado@outlook.com"}],"ativo": false}},
    {"method": "DELETE", "url": "/pessoas/3"}
  ]
}
'''


PATCH /pessoas/ativarOuDesativar
'''
  [
  	{
  		"id" :1,
		"ativo": false
  	},
    {
  		"id" :2,
		"ativo": true
  	},
    {
      "id":4,
      "ativo":false
    }
  ]
'''