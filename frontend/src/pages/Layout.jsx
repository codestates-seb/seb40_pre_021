import { Outlet } from 'react-router-dom';
import Header from '../components/Header/Header';
import LeftSideBar from '../components/LeftSideBar/LeftSideBar';
import styled from 'styled-components';
import Footer from '../components/Footer/Footer';
import { useEffect } from 'react';
import { AccessTokenRefresh } from '../api/TokenApi';
import { useDispatch } from 'react-redux';
import { loginSuccess } from '../modules/userReducer';

const Body = styled.div`
	display: flex;
	justify-content: center;
`;
const OutletSize = styled.div`
	width: 1100px;
`;

const Layout = () => {
	const dispatch = useDispatch();
	useEffect(() => {
		AccessTokenRefresh().then((res) => {
			dispatch(loginSuccess(res));
		});
	}, []);
	return (
		<>
			<Header />
			<Body>
				<LeftSideBar />
				<OutletSize>
					<Outlet />
				</OutletSize>
			</Body>
			<Footer />
		</>
	);
};

export default Layout;
