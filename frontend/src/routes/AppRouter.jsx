import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import { Suspense, lazy } from 'react';
import Loading from '../components/common/Loading';

const Home = lazy(() => import('../pages/Home'));
const Ask = lazy(() => import('../pages/Ask'));
const Question = lazy(() => import('../pages/Question'));
const Layout = lazy(() => import('../pages/Layout'));
const Login = lazy(() => import('../pages/Login'));
const Mypage = lazy(() => import('../pages/Mypage/Mypage'));
const MypageActivityPage = lazy(() =>
	import('../pages/Mypage/MypageActivityPage'),
);
const MypageSavesPage = lazy(() => import('../pages/Mypage/MypageSavesPage'));
const Questions = lazy(() => import('../pages/Questions'));
const Signup = lazy(() => import('../pages/Signup'));

const AppRouter = () => {
	return (
		<BrowserRouter>
			<Suspense fallback={<Loading />}>
				<Routes>
					<Route element={<Layout />}>
						{/*여기안에 다른페이지들 route 적으면 됩니다. 
          ex) <Route path="/mypage" element={<Mypage />}>
          */}
						<Route path="/" element={<Home />} />
						<Route path="/questions" element={<Questions />} />
						<Route path="/questions/ask" element={<Ask />} />
						<Route path="/questions/question" element={<Question />} />
						{/* ask와 question은 테스트 후 삭제해야 합니다 (메인 화면에서 직접 연결되는 것이 아님)*/}
						<Route path="/users" element={<Mypage />}>
							<Route path="activity" element={<MypageActivityPage />} />
							<Route path="saves" element={<MypageSavesPage />} />
						</Route>
					</Route>
					<Route path="/login" element={<Login />} />
					<Route path="/signup" element={<Signup />} />
					<Route path="*" element={<Navigate to="/" replace />} />
				</Routes>
			</Suspense>
		</BrowserRouter>
	);
};

export default AppRouter;
