function addReimbursement() {
    // Collect user input...
    let employee_id = document.getElementById('employeeId').value;
    let claim_date = document.getElementById('claimDate').value;
    let event_start_date = document.getElementById('eventStartDate').value;
    let event_end_date = document.getElementById('eventEndDate').value;
    let cost = document.getElementById('cost').value;
    let event_location = document.getElementById('eventLocation').value;
    let tuition_type = document.querySelector('input[name="tuitionType"]:checked').value;
    let work_justification = document.getElementById('workJustification').value;
    let event_name = document.getElementById('eventName').value;

    // and save into an object
    let data = {
            employeeId: employee_id,
            claimDate: claim_date,
            eventStartDate: event_start_date,
            eventEndDate: event_end_date,
            cost: cost,
            eventLocation: event_location,
            tuitionType: tuition_type,
            workJustification: work_justification,
            eventName: event_name
    }

    console.log(data);

    // prepare and send our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {

            let r = this.responseText;
            console.log(r);
        }
    }
    xhttp.open('POST', 'http://localhost:8080/trms/reimbursements', true);
    // because we are sending data in the body, we need to tell our server what to expect
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(data));

    window.history.back();
}

function getReimbursements() {

    let url = 'http://localhost:8080/trms/reimbursements';
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        console.log(this.readyState);

        if (this.readyState == 4) {
            let r = this.responseText;
            r = JSON.parse(r);
            console.log(r);
        }
    }

    xhttp.open('GET', url, true);
    xhttp.send();
}

/***
 * FETCH API METHOD
 * 
 */

async function getReimbursementsFetch() {

    let url = 'http://localhost:8080/trms/reimbursements';
    let res = await fetch(url)
    let data = await res.json()

    // the promise method then()
    .then(data => {
        console.log(data);
        populateData(data);
    })
    .catch(err => console.log(err));

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

function capitalize(str) {
    if (str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    } else {
        return '';
    }
}
