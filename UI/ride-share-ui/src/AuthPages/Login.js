import "./authPages.css"
const Login = () => {
	return (
		<div>
			<h1>Login</h1>
            <div>
                <p>User Id</p>
                <input type="text" id="fname" name="fname"></input>
            </div>
            <div>
                <p>Password</p>
                <input type="password" id="fname" name="fname"></input>
            </div>
		</div>
	);
};

export default Login;
