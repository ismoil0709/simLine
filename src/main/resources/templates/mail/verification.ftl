<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Verification</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333333;
        }

        p {
            color: #555555;
        }

        .verification-link {
            display: block;
            margin-top: 20px;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            color: #ffffff;
            background-color: #007bff;
            border-radius: 5px;
        }

        .footer {
            margin-top: 30px;
            text-align: center;
            color: #777777;
        }

        .company-logo {
            max-width: 100px;
            margin: 0 auto;
            display: block;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>Email Verification</h1>
    <p>Thank you for signing up. To complete your registration, please click the link below to verify your email
        address:</p>
    <a href="${link}" class="verification-link">Verify Email Address</a>
    <p>If you did not sign up for our service, please ignore this email.</p>
    <div class="footer">
        <p>Best Regards,<br>Spring Mail</p>
    </div>
</div>
</body>

</html>
