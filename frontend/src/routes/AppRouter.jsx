import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from '../pages/Home';
import Ask from '../pages/Ask';
import Layout from '../pages/Layout';
import Login from '../pages/Login';
import Mypage from '../pages/Mypage';
import Questions from '../pages/Questions';
import Signup from '../pages/Signup';

const AppRouter = () => {
	return (
		<BrowserRouter>
			<Routes>
				<Route element={<Layout />}>
					{/*여기안에 다른페이지들 route 적으면 됩니다. 
          ex) <Route path="/mypage" element={<Mypage />}>
          */}
					<Route path="/" element={<Home />} />
					<Route path="/questions" element={<Questions />} />
					<Route path="/users" element={<Mypage />} />
					<Route path="/ask" element={<Ask />} />
				</Route>
				<Route path="/login" element={<Login />} />
				<Route path="/signup" element={<Signup />} />
			</Routes>
		</BrowserRouter>
	);
};

export default AppRouter;
