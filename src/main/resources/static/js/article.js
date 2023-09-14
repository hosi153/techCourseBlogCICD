const newSaveButton = document.getElementById("saveBTN");
const deleteButton = document.getElementById("delBTN");
const editButton = document.getElementById("editSaveBTN");
const loginButton = document.getElementById("loginBTN");
const viewOnlyButton = document.getElementById("viewBTN");
const makeNewButton = document.getElementById("newMemberBTN");

if(loginButton){
    loginButton.addEventListener('click',event =>{
        let loginId = document.getElementById('loginId').value;
        console.log(`login req id : ${loginId}`)
        fetch(`/api/member/${loginId}`,{
            method :'GET',
            headers : {"Content-Type": "application/json",}
        })
            .then((response) => {
                if(response.ok){return response.json()}
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            }).then(data => {
                sessionStorage.setItem('id', data.loginId);
                sessionStorage.setItem('name', data.name);
                location.href=`/articles`;
            })
            .catch(error => {
                alert('ID를 다시 입력해 주세요.');
            })
    })
}

if(makeNewButton){
    makeNewButton.addEventListener('click',event =>{
        fetch('/api/member/new',{
            method :'POST',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                loginId:document.getElementById("newId").value,
                loginPwd: document.getElementById("newPwd").value,
                name: document.getElementById("newName").value,
            })
        })
            .then((response)=> {
                if(response.ok) alert('등록 완료되었습니다.');
                else alert('다른 정보를 입력해 주세요.')
            });
    })
}

if(newSaveButton){
    newSaveButton.addEventListener('click',event =>{
        fetch('/api/new',{
            method :'POST',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                title:document.getElementById("title").value,
                content: document.getElementById("content").value,
                loginId: sessionStorage.getItem('id')
                // member:document.getElementById("author").value
            })
        })
            .then(()=> {
                alert('등록 완료되었습니다.');
                location.href=`/articles`;
            });
    })
}

if(editButton){
    let articleId = document.getElementById('articleId').value;
    editButton.addEventListener('click',event =>{
        fetch(`/api/${articleId}`,{
            method :'PATCH',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                content: document.getElementById("editContent").value,
                // member:document.getElementById("author").value
            })
        })
            .then(()=> {
                alert('수정 완료되었습니다.');
                location.href=`/articles/${articleId}`;
            });
    })
}

if(deleteButton){
    let articleId = document.getElementById('articleId').value;
    deleteButton.addEventListener('click', event =>{
        fetch(`/api/${articleId}`,{
            method : 'DELETE',
            headers : {
                "Content-Type": "application/json",
            }
        })
            .then(() => {
                alert(`${articleId} 삭제하였습니다.`);
                location.replace('/articles');
            })
    });
}

if(viewOnlyButton){
    // location.href = `/articles/only/${sessionStorage.getItem('loginId')}`;
    viewOnlyButton.addEventListener('click', event =>{
        fetch(`/articles/only/${sessionStorage.getItem('id')}`,{
            method :'GET',
            headers : {"Content-Type": "application/json",}
        }).then((response) => {
            if(response.ok){
                location.href=`/articles/only/${sessionStorage.getItem('id')}`
            }
            else{throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);}
        }).catch(error => {
            alert('사용자 정보가 없습니다.');
        })
        //location.href = `/articles/only/${sessionStorage.getItem('id')}`;
    });
}

