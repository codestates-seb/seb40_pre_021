import { Outlet } from 'react-router-dom';
import Header from '../components/Header/Header';
import LeftSideBar from '../components/LeftSideBar/LeftSideBar';
import styled from 'styled-components';
import Footer from '../components/Footer/Footer';

const Body = styled.div`
	display: flex;
	padding: 0;
`;
const Layout = () => {
	return (
		<>
			<Header />
			<Body>
				<LeftSideBar />
				<Outlet />
			</Body>
			<Footer />
		</>
	);
};

export default Layout;
