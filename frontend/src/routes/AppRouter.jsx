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
						{/* 왼쪽 Quesions 메뉴 */}
						<Route path="/questions" element={<Questions />}></Route>
						<Route
							path="/questions/question/:questionId"
							element={<Question />}
						/>
						{/* keyword 검색 */}
						<Route path="/search/:q" element={<Questions />} />

						<Route path="/users" element={<Mypage />}>
							<Route path="activity" element={<MypageActivityPage />} />
							<Route path="saves" element={<MypageSavesPage />} />
						</Route>
					</Route>
					<Route path="/login" element={<Login />} />
					<Route path="/signup" element={<Signup />} />
					<Route path="/questions/ask" element={<Ask />} />
					<Route path="*" element={<Navigate to="/" replace />} />
				</Routes>
			</Suspense>
		</BrowserRouter>
	);
};

export default AppRouter;
