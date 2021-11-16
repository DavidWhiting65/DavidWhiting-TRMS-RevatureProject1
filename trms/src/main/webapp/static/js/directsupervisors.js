async function getSupervisedPendingReimbursementsFetch() {
    let direct_supervisor_id = document.getElementById('directSupervisorId').value;
    let url = 'http://localhost:8080/trms/reimbursements/s' + direct_supervisor_id;
    let res = await fetch(url)
    let data = await res.json()

    .then(data => {
        console.log(data);
        populateData(data);
    })
    .catch(err => console.log(err));
}

async function getAllSupervisedReimbursementsFetch() {
    let direct_supervisor_id = document.getElementById('directSupervisorId2').value;
    let url = 'http://localhost:8080/trms/reimbursements/q' + direct_supervisor_id;
    let res = await fetch(url)
    let data = await res.json()

    .then(data => {
        console.log(data);
        populateData(data);
    })
    .catch(err => console.log(err));
}

function updateGrade() {

    let reimbursement_id = document.getElementById('reimbursementId5').value;
    let grade = document.getElementById('grade').value;
    let data = {
                reimbursementId: reimbursement_id,
                grade: grade
        }
    console.log(data);
    let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let r = this.responseText;
                console.log(r);
            }
        }
        xhttp.open('PUT', 'http://localhost:8080/trms/reimbursements/g', true);
        // because we are sending data in the body, we need to tell our server what to expect
        xhttp.setRequestHeader('Content-Type', 'application/json');
        xhttp.send(JSON.stringify(data));
}

function approveReimbursement() {

    let reimbursement_id = document.getElementById('reimbursementId3').value;
    let is_direct_supervisor_approved = true;
        let data = {
                    reimbursementId: reimbursement_id,
                    isDirectSupervisorApproved: is_direct_supervisor_approved
            }
        console.log(data);
        let xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let r = this.responseText;
                    console.log(r);
                }
            }
            xhttp.open('PUT', 'http://localhost:8080/trms/reimbursements/b', true);
            // because we are sending data in the body, we need to tell our server what to expect
            xhttp.setRequestHeader('Content-Type', 'application/json');
            xhttp.send(JSON.stringify(data));
}

function declineReimbursement() {

    let reimbursement_id = document.getElementById('reimbursementId2').value;
    let direct_supervisor_message = document.getElementById('directSupervisorMessage').value;
    let is_declined = true;
    let is_pending = false;
        let data = {
                    reimbursementId: reimbursement_id,
                    directSupervisorMessage: direct_supervisor_message,
                    isDeclined: is_declined,
                    isPending: is_pending
            }
        console.log(data);
        let xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let r = this.responseText;
                    console.log(r);
                }
            }
            xhttp.open('PUT', 'http://localhost:8080/trms/reimbursements/d', true);
            // because we are sending data in the body, we need to tell our server what to expect
            xhttp.setRequestHeader('Content-Type', 'application/json');
            xhttp.send(JSON.stringify(data));
}

function requestAdditionalInfo() {

    let reimbursement_id = document.getElementById('reimbursementId4').value;
    let is_request_additional_info = true;
        let data = {
                    reimbursementId: reimbursement_id,
                    isRequestAdditionalInfo: is_request_additional_info
            }
        console.log(data);
        let xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let r = this.responseText;
                    console.log(r);
                }
            }
            xhttp.open('PUT', 'http://localhost:8080/trms/reimbursements/r', true);
            // because we are sending data in the body, we need to tell our server what to expect
            xhttp.setRequestHeader('Content-Type', 'application/json');
            xhttp.send(JSON.stringify(data));
}

function populateData(res) {

    let lineBreak = document.createElement("br");

    let dataSection = document.getElementById('data');

    let nameTag = document.createElement('h3');

    nameTag.innerHTML += "<br>REIMBURSEMENTS:";

    dataSection.appendChild(nameTag);
    dataSection.appendChild(lineBreak);

    let nameTag2 = document.createElement('p');

    for(let i = 0; i < res.length; i++) {
        let lineBreak = document.createElement("br");

        console.log("res[i].eventName = " + res[i].eventName);

        nameTag2.innerHTML += "Event Name: " + res[i].eventName;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Reimbursement ID: " + res[i].reimbursementId;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Employee ID: " + res[i].employeeId;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Direct Supervisor ID: " + res[i].directSupervisorId;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += 'Claim Date: ' + new Date(res[i].claimDate);
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Cost: " + res[i].cost;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Claim Amount: " + res[i].claimAmount;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Event Start Date: " + new Date(res[i].eventStartDate);
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Event End Date: " + new Date(res[i].eventEndDate);
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Event Location: " + res[i].eventLocation;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Tution Type: " + res[i].tuitionType;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Work Justification: " + res[i].workJustification;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Grade: " + res[i].grade;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Direct Supervisor Message: " + res[i].directSupervisorMessage;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Pending?: " + res[i].isPending;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Direct Supervisor Approved?: " + res[i].isDirectSupervisorApproved;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Department Head Approved?: " + res[i].isDepartmentHeadApproved;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Awarded?: " + res[i].isAwarded;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Approved Amount: " + res[i].approvedAmount;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Declined?: " + res[i].isDeclined;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Archived?: " + res[i].isArchived;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Presentation Uploaded?: " + res[i].isPresentationUploaded;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Additional Info Requested?: " + res[i].isRequestAdditionalInfo;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Additional Info: " + res[i].additionalInfo;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Urgent? " + res[i].isUrgent;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Department Head? " + res[i].isDepartmentHead;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "Available Balance: " + res[i].availableBalance;
        nameTag2.innerHTML += "<br>";
        nameTag2.innerHTML += "<br>";

        dataSection.appendChild(nameTag2);
    }
}
