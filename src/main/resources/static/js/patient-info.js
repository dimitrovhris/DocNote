document.addEventListener('DOMContentLoaded', function () {
    const fetchButton = document.getElementById('show-info');

    fetchButton.addEventListener('click', function () {
        fetch('/patient/showInfo/' + patientId)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                const firstName = response.headers.get("firstName");
                const surname = response.headers.get("surname");
                const lastName = response.headers.get("lastName");
                const birthday = response.headers.get("birthday");
                const egn = response.headers.get("egn");
                const address = response.headers.get("address");
                const height = response.headers.get("height");
                const weight = response.headers.get("weight");
                let mainInfo = document.querySelector(".main-info");

                let div1 = document.createElement("div");
                div1.setAttribute("id", "inner-info");
                let h11 = document.createElement("h1");
                h11.innerText = 'Main info:';
                let h21 = document.createElement("h2");
                h21.innerText = firstName;
                let h22 = document.createElement("h2");
                h22.innerText = surname;
                let h23 = document.createElement("h2");
                h23.innerText = lastName;
                div1.appendChild(h11);
                div1.appendChild(h21)
                div1.appendChild(h22)
                div1.appendChild(h23)

                let div2 = document.createElement("div");
                div2.setAttribute("id", "other-info");
                let h24 = document.createElement("h2");
                h24.innerText = 'Birthday: ' + birthday;
                let h25 = document.createElement("h2");
                h25.innerText = 'Id: ' + egn;
                let h26 = document.createElement("h2");
                h26.innerText = 'Address: ' + address;
                div2.appendChild(h24);
                div2.appendChild(h25);
                div2.appendChild(h26);

                let div3 = document.createElement("div");
                div3.setAttribute("id", "body-info");
                let h27 = document.createElement("h2");
                h27.innerText = 'Height: ' + height + ' cm';
                let h28 = document.createElement("h2");
                h28.innerText = 'Weight: ' + weight + ' kg';
                div3.appendChild(h27);
                div3.appendChild(h28);

                mainInfo.appendChild(div1);
                mainInfo.appendChild(div2);
                mainInfo.appendChild(div3);
                    if (fetchButton.style.display === "none") {
                            fetchButton.style.display = "block";
                    } else {
                            fetchButton.style.display = "none";
                    }
            })
    })
})