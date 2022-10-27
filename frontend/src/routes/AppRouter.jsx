import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from '../components/Home/Home';
import Questions from '../components/Questions/Questions';
import Layout from '../pages/Layout';
import Login from '../pages/Login';

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
				</Route>
				<Route path="/login" element={<Login />} />
			</Routes>
		</BrowserRouter>
	);
};

export default AppRouter;
