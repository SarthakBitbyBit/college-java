<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>student registration form</title>
    <style>
        body { font-family: sans-serif; background: #f8fafc; margin: 40px; color: #1e293b; }
        .box { background: #ffffff; padding: 24px; border-radius: 8px; max-width: 500px; margin: 0 auto; box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        h2 { margin-top: 0; font-size: 18px; color: #0f172a; }
        label { display: block; margin-top: 12px; font-size: 13px; font-weight: bold; color: #475569; }
        input { width: 100%; padding: 8px 10px; margin-top: 4px; box-sizing: border-box; border: 1px solid #cbd5e1; border-radius: 4px; font-size: 14px; }
        button { margin-top: 20px; width: 100%; padding: 10px; background: #2563eb; color: white; border: none; border-radius: 4px; font-weight: bold; cursor: pointer; }
        button:hover { background: #1d4ed8; }
        .back { display: block; margin-top: 14px; text-align: center; color: #64748b; font-size: 13px; text-decoration: none; }
    </style>
</head>
<body>
    <div class="box">
        <h2>student registration</h2>
        <form action="register_result.jsp" method="post">
            <label for="name">full name</label>
            <input type="text" id="name" name="name" required placeholder="e.g. Priya Patel">

            <label for="email">email address</label>
            <input type="email" id="email" name="email" required placeholder="e.g. priya.patel@example.com">

            <label for="phone">phone number (10 digits)</label>
            <input type="tel" id="phone" name="phone" required placeholder="e.g. 9876543210" pattern="[0-9]{10}">

            <label for="password">account password</label>
            <input type="password" id="password" name="password" required placeholder="minimum 6 characters">

            <button type="submit">complete registration</button>
        </form>
        <a class="back" href="index.jsp">&larr; return to assignment home</a>
    </div>
</body>
</html>
